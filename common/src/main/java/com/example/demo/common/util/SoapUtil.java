package com.example.demo.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import javax.xml.soap.*;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author acemma
 * @date 2018/1/12 15:39
 * @Description
 */
public class SoapUtil {

    private static Logger logger = LoggerFactory.getLogger(SoapUtil.class);

    /**
     * 返回JSONArray格式的结果
     *
     * params中如果同一个层次有重名元素，需要将重名元素放到list中，key不变，value使用新的list
     * @param prefix 方法前缀
     * @param methodName 方法名
     * @param ns 命名空间
     * @param params 参数
     * @param wsdl wsdl
     * @return
     * @throws SOAPException 生成soap报文时发生异常
     */
    public static JSONArray performRequest(String prefix, String methodName, String ns, Map<String, Object> params, String wsdl) throws SOAPException
    {
        return parseXML(getSOAPMessage(prefix, methodName, ns, params, wsdl));
    }

    /**
     *
     * @param prefix 方法前缀
     * @param methodName 方法名
     * @param ns 命名空间
     * @param params 参数
     * @param wsdl wsdl
     * @return
     * @throws SOAPException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    private static String getSOAPMessage(String prefix, String methodName, String ns, Map<String, Object> params, String wsdl) throws SOAPException
    {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage message = messageFactory.createMessage();
        message.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "UTF-8");

        SOAPPart soapPart = message.getSOAPPart();// 创建soap部分
        SOAPEnvelope envelope = soapPart.getEnvelope();

        envelope.setAttribute("xmlns:" + prefix, ns);

        SOAPBody body = envelope.getBody();
        SOAPElement bodyElement = body.addChildElement(envelope.createName(methodName, prefix, ""));

        if (null != params)
        {
            for (Map.Entry<String, Object> entry : params.entrySet())
            {
                if (entry.getValue() instanceof Map)
                {
                    SOAPElement inputParam = bodyElement.addChildElement(entry.getKey());
                    addParams((Map<String, Object>)entry.getValue(), inputParam);
                }
                else if (entry.getValue() instanceof String)
                {
                    bodyElement.addChildElement(entry.getKey()).addTextNode((String)entry.getValue());
                }
                else if (entry.getValue() instanceof List)
                {
                    for(Object item : (List)entry.getValue())
                    {
                        SOAPElement inputParam = bodyElement.addChildElement(entry.getKey());

                        addParams((Map<String, Object>)item, inputParam);
                    }
                }
                else if (entry.getValue() instanceof Integer
                        || entry.getValue() instanceof Long
                        || entry.getValue() instanceof Double
                        || entry.getValue() instanceof Short
                        || entry.getValue() instanceof Float)
                {
                    bodyElement.addChildElement(entry.getKey()).addTextNode(String.valueOf(entry.getValue()));
                }
            }
        }

        message.saveChanges();

        String xmlString = "";
        try
        {
            xmlString = getXMLMessage(message);

//            logger.debug(xmlString);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return getResultFromWebService(xmlString, wsdl);
    }


    @SuppressWarnings("unchecked")
    private static void addParams(Map<String, Object> params, SOAPElement parentElement) throws SOAPException
    {
        if (null != params)
        {
            for (Map.Entry<String, Object> entry : params.entrySet())
            {
                if (entry.getValue() instanceof Map)
                {
                    SOAPElement element = parentElement.addChildElement(entry.getKey());
                    addParams((Map<String, Object>)entry.getValue(), element);
                }
                else if (entry.getValue() instanceof String)
                {
                    parentElement.addChildElement(entry.getKey()).addTextNode((String)entry.getValue());
                }
                else if (entry.getValue() instanceof List)
                {
                    for(Object item : (List)entry.getValue())
                    {
                        SOAPElement inputParam = parentElement.addChildElement(entry.getKey());

                        addParams((Map<String, Object>)item, inputParam);
                    }
                }
            }
        }
    }

    private static String getXMLMessage(SOAPMessage msg) throws SOAPException, IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        msg.writeTo(baos);
        String str = baos.toString("utf-8");
        baos.close();
        return str;
    }

    /**
     * 请求WebService获取结果
     *
     * @param xml
     * @param endPoint
     * @return
     */
    private static String getResultFromWebService(String xml, String endPoint)
    {

        String result = "";
        CloseableHttpResponse response = null;
        BufferedReader in = null;
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(endPoint);
            httpPost.addHeader("Content-Type","text/xml;charset=UTF-8");

            //解决中文乱码问题
            StringEntity stringEntity = new StringEntity(xml,"UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("text/xml");
            httpPost.setEntity(stringEntity);
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            // 执行
            int statusCode = response.getStatusLine().getStatusCode();
            // 判断是否执行成功
            if (statusCode != HttpStatus.SC_OK) {
                logger.error("Method failed: " + response.getStatusLine());
            }
            else {
                in = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        logger.debug("结果：" + result);
        return result;
    }

    /**
     * 解析xml字符串
     *
     * @param xmlDoc
     *            调用wsdl返回的数据
     * @return 请求结果，如果网络连接失败，返回null
     */
    private static JSONArray parseXML(String xmlDoc) {
        JSONArray resultArray = null;
        StringReader read = new StringReader(xmlDoc);
        // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
        InputSource source = new InputSource(read);
        // 创建一个新的SAXBuilder
        SAXBuilder sb = new SAXBuilder();
        // List infoList = new ArrayList();
        try {
            // 通过输入源构造一个Document
            Document doc = sb.build(source);
            // 取的根元素
            Element root = doc.getRootElement();
            // 得到根元素所有子元素的集合
            // Namespace ns = root.getNamespace();
            List<Element> bodyList = root.getChildren();
            if (bodyList != null && bodyList.size() > 0)
            {
                Element body = (Element) bodyList.get(1);// Body
                List<Element> responses = body.getChildren();
                if (responses != null && responses.size() > 0)
                {
                    Element response = (Element) responses.get(0);
                    List<Element> results = response.getChildren();
                    if (results != null && results.size() > 0)
                    {
                        resultArray = new JSONArray();
                        for (int i = 0; i < results.size(); i++)
                        {
                            Element result = (Element) results.get(i);

                            resultArray.add(convertToJSONOrText(result));
                        }
                    }
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resultArray;
    }

    private static Object convertToJSONOrText(Element item)
    {

        List<Element> elements = item.getChildren();

        if(elements.size() != 0)
        {
            JSONObject map = new JSONObject();
            for(Element element : elements)
            {
                int count = 0;
                String key = element.getName();
                //有些接口，返回的数据是重名的
                if(map.containsKey(key))
                {
                    count = getSameKeyCount(map, key);
                    if(0 != count)
                    {
                        if(1 == count)
                        {
                            key = element.getName() + "_@@_start_" + count;
                        }
                        else
                        {
                            key = element.getName() + "_@@_" + count;
                        }
                    }
                }

                map.put(key, convertToJSONOrText1(element));
            }
            map = reformatMap(map);

            return map;
        }
        else
        {
            return item.getText();
        }

    }


    private static Object convertToJSONOrText1(Element item)
    {

        List<Element> elements = item.getChildren();

        if(elements.size() != 0)
        {
            JSONObject map = new JSONObject();
            for(Element element : elements)
            {
                int count = 0;
                String key = element.getName();
                //有些接口，返回的数据是重名的
                if(map.containsKey(key))
                {
                    count = getSameKeyCount(map, key);
                    if(0 != count)
                    {
                        if(1 == count)
                        {
                            key = element.getName() + "_@@_start_" + count;
                        }
                        else
                        {
                            key = element.getName() + "_@@_" + count;
                        }
                    }
                }

                map.put(key, convertToJSONOrText1(element));
            }
            map = reformatMap(map);

            return map;
        }
        else
        {
            return item.getText();
        }

    }

    /**
     * map中以key为开始的条目数量
     * @param map
     * @return
     */
    private static int getSameKeyCount(JSONObject map, String key)
    {
        int count = 0;
        for(Map.Entry<String, Object> tmp : map.entrySet())
        {
            if(tmp.getKey().startsWith(key))
            {
                count++;
            }
        }
        return count;
    }

    /**
     * 将带索引的key对应的数据下移一层
     * @param map
     */
    private static JSONObject reformatMap(JSONObject map)
    {
        JSONObject result = new JSONObject();
        for(Map.Entry<String, Object> entry : map.entrySet())
        {
            //当前key还有后续带索引的数据
            String key = entry.getKey();

            if(map.containsKey(key+"_@@_start_1"))
            {
                JSONArray array = new JSONArray();


                array.add(map.get(key));
                array.add(map.get(key+"_@@_start_1"));

                int size = map.entrySet().size();
                for(int i=2; i<size; i++)
                {
                    Object object = map.get(key + "_@@_" + i);
                    if(null != object)
                    {
                        array.add(object);
                    }
                }

                result.put(key, array);

            }
            else if(-1 == key.indexOf("_@@_"))
            {
                result.put(key, entry.getValue());
            }
        }

        return result;
    }


    public static void main(String[] args) {
        JSONArray jsonArray = parseXML("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns2:GetTerminalDetailsResponse xmlns:ns2=\"http://api.jasperwireless.com/ws/schema\" ns2:requestId=\"W2CgYVUm2ox4G642\"><ns2:correlationId>TCE-100-ABC-34084</ns2:correlationId><ns2:version>7.28</ns2:version><ns2:build>Unknown</ns2:build><ns2:timestamp>2018-01-12T07:27:15.252Z</ns2:timestamp><ns2:terminals><ns2:terminal msisdn=\"861064619000413\"><ns2:iccid>8986061501000089113</ns2:iccid><ns2:customer>途趣（长沙）</ns2:customer><ns2:suspended>N</ns2:suspended><ns2:ratePlan>测试-0元-200M-50SMS-资费计划</ns2:ratePlan><ns2:status>ACTIVATED_NAME</ns2:status><ns2:monthToDateUsage>0.000</ns2:monthToDateUsage><ns2:overageLimitReached>false</ns2:overageLimitReached><ns2:overageLimitOverride>DEFAULT</ns2:overageLimitOverride><ns2:dateActivated>2017-02-17T15:36:21.828Z</ns2:dateActivated><ns2:dateAdded>2015-05-15T09:27:33.069Z</ns2:dateAdded><ns2:dateModified>2017-02-17T15:42:57.743Z</ns2:dateModified><ns2:dateShipped>2015-05-20T00:00:00.000Z</ns2:dateShipped><ns2:monthToDateDataUsage>0.000</ns2:monthToDateDataUsage><ns2:monthToDateSMSUsage>0</ns2:monthToDateSMSUsage><ns2:monthToDateVoiceUsage>0</ns2:monthToDateVoiceUsage><ns2:secureSimId/><ns2:custom1>浏阳市</ns2:custom1><ns2:custom2/><ns2:custom3/><ns2:rating/><ns2:secureSimUsernameCopyRule>N</ns2:secureSimUsernameCopyRule><ns2:secureSimPasswordCopyRule>N</ns2:secureSimPasswordCopyRule><ns2:accountId>100042017</ns2:accountId><ns2:fixedIpAddress xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/><ns2:ctdSessionCount xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/><ns2:customerCustom1/><ns2:customerCustom2/><ns2:customerCustom3/><ns2:customerCustom4/><ns2:customerCustom5/><ns2:operatorCustom1>湖南朱征源</ns2:operatorCustom1><ns2:operatorCustom2/><ns2:operatorCustom3/><ns2:operatorCustom4/><ns2:operatorCustom5/><ns2:imsi>460069001000413</ns2:imsi><ns2:primaryICCID/><ns2:imei/><ns2:globalSimType/><ns2:version>1</ns2:version><ns2:custom4/><ns2:custom5/><ns2:custom6/><ns2:custom7/><ns2:custom8/><ns2:custom9/><ns2:custom10/></ns2:terminal></ns2:terminals></ns2:GetTerminalDetailsResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>");
        System.out.println(jsonArray);
    }

}

