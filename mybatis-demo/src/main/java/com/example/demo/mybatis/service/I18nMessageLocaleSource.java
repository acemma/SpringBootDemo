package com.example.demo.mybatis.service;

import com.example.demo.common.share.I18nMessageSource;

import java.util.Locale;

/**
 * @author acemma
 * @date 2018/11/22 16:31
 * @Description
 */
public interface I18nMessageLocaleSource extends I18nMessageSource {

    /**
     * 获得国际化字符串
     *
     * @param code i18n编码
     * @param code i18n语言环境
     * @return
     */
    String getMessage(String code, Locale locale);

}
