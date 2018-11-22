package com.example.demo.common.util;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author acemma
 * @date 2018/7/26 15:36
 * @Description
 */
public class StringUtil extends StringUtils{

    /**
     * 15位身份证号正则表达式
     */
    private static final String REG_EXP_15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{2}[0-9Xx]$";

    /**
     * 18位身份证号正则表达式
     */
    private static final String REG_EXP_18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}[0-9Xx]$";



    /**
     * 解析XML字符串
     * @param xmlStr
     * @return
     */
    public static Map<String,Object> parseXml(String xmlStr){
        StringReader read = new StringReader(xmlStr);
        InputSource source = new InputSource(read);
        SAXBuilder sb = new SAXBuilder();
        try {
            Document doc = sb.build(source);
            Element root = doc.getRootElement();
            return parse(root);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String,Object> parse(Element root){
        Map<String,Object> result = new HashMap<>();

        List nodes = root.getChildren();
        int len = nodes.size();
        if (len == 0){
            result.put(root.getName(),root.getText());
        }else {
            for(int i=0;i<len;i++){
                //循环依次得到子元素
                Element element = (Element) nodes.get(i);
                result.put(element.getName(),element.getText());
                parse(element);
            }
        }
        return result;
    }


    /**
     * 身份证号码校验 简单验证
     * @param certCode
     * @return
     */
    public static boolean validateCertCode(String certCode){
        boolean flag = false;
        //15位身份证和18位身份证格式校验
        if (Pattern.matches(REG_EXP_15,certCode) || Pattern.matches(REG_EXP_18,certCode)){
            flag = true;
        }
        if (flag){
            //合法性校验
            if (certCode.length() == 18){
                //号码前17位
                String front17 = certCode.substring(0, certCode.length() - 1);
                //校验位(最后一位)
                String verify = certCode.substring(17, 18);
                return checkVerify(front17, verify);
            }
        }

        return flag;
    }

    public static boolean checkVerify(String front17,String verify){
        int[] wi = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1};
        String[] vi = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
        int s = 0;
        for(int i = 0; i<front17.length(); i++){
            int ai = Integer.parseInt(front17.charAt(i) + "");
            s += wi[i]*ai;
        }
        int y = s % 11;
        String v = vi[y];
        if(!(verify.toUpperCase().equals(v))){
            return false;
        }
        return true;
    }



}
