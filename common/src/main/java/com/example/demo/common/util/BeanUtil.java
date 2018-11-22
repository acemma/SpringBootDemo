package com.example.demo.common.util;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * @author acemma
 * @date 2018/7/23 16:57
 * @Description
 */
public class BeanUtil {

    private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);


    public static void validateBean(Object object){
        if (object == null){
            return;
        }
        try {
            Class clazz = object.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields){
                field.setAccessible(true);
                NotBlank notBlankAnno = field.getAnnotation(NotBlank.class);
                if (null != notBlankAnno){
                    //获取字段名称
                    String fieldName = field.getName();
                    //获取字段值
                    Object fieldValue = field.get(object);
                    if (null == fieldValue || "".equals(fieldValue)){
                        if ("".equals(notBlankAnno.message())){
                            logger.info(fieldName + "字段不能为空");
                        }else {
                            logger.info(notBlankAnno.message());
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
