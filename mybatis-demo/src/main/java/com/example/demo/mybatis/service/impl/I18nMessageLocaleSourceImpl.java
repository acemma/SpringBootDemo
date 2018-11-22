package com.example.demo.mybatis.service.impl;

import com.example.demo.mybatis.service.I18nMessageLocaleSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @author acemma
 * @date 2018/11/22 16:31
 * @Description
 */
@Service
public class I18nMessageLocaleSourceImpl implements I18nMessageLocaleSource{

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getMessage(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, null, locale);
    }

    @Override
    public String getMessage(String code, Locale locale) {
        return messageSource.getMessage(code, null, locale);
    }

}
