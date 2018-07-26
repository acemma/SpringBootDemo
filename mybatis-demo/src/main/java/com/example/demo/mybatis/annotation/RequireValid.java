package com.example.demo.mybatis.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

/**
 * @author acemma
 * @date 2018/7/5 21:23
 * @Description
 */
@Target({ TYPE,PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireValid {
}
