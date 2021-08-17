package com.zcy.test.demain.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author fanxiaozhen
 */
@Slf4j
public class NumUtil {

    public static <T> T string2Num(String source, Class<T> target) {

        if (StringUtils.isBlank(source)) {
            return null;
        }
        try {
            Constructor<T> constructor = target.getConstructor(String.class);//获取一个构造器一类的东西
            return constructor.newInstance(source);// 进行实例化
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException
                | InvocationTargetException e) {
            log.warn("string to num error, source:{}", source, e);
            return null;
        }
    }

}
