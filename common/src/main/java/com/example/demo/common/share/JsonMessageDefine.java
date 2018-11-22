package com.example.demo.common.share;/**
 * Created by fanbeibei on 2017/9/19.
 */

import com.example.demo.common.util.StringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Fan Beibei
 * @Description: 描述
 * @date 2017/9/19 15:45
 */
public class JsonMessageDefine {

    /**
     * 正常情况
     */
    public final static String SUCCESS = "SUCCESS";

    /**
     * 正常情况
     */
    public final static String OP_FAILED = "OP_FAILED";

    /**
     * 手机号不正确
     */
    public static final String PHONE_NUMBER_ERROR = "PHONE_NUMBER_ERROR";



    private static Map<String, String> messageMap = new ConcurrentHashMap<>();

    static {
        messageMap.put(SUCCESS, "");
        messageMap.put(PHONE_NUMBER_ERROR, "phone.number.error");

    }

    public static String getMessage(String code) {
        return messageMap.get(code);
    }

    /**
     * 多语言转换
     *
     * @param code              提示编码
     * @param i18nMessageSource 国际化接口实现
     * @return
     */
    public static String getMessage(String code, I18nMessageSource i18nMessageSource) {
        String msg = messageMap.get(code);
        if (StringUtil.isBlank(msg)) {
            return msg;
        }
        return i18nMessageSource.getMessage(msg);
    }


}
