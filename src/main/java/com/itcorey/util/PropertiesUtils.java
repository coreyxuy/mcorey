package com.itcorey.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by ：Corey
 * 17:33 2018/12/25
 * 配置文件工具类
 */
public class PropertiesUtils {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    private static Properties props;

    static {
        String fileName = "mmall.properties";
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"utf-8"));
        } catch (IOException e) {
            logger.error("读取配置文件一场",e);
        }
    }

    public static String getProperty(String key){
        String value = props.getProperty(key.trim());
        if (StringUtils.isBlank(value)){
            return  null;
        }
        return value.trim();
    }


    public static String getProperty(String key,String defaultValue){
        String value = props.getProperty(key.trim());
        if (StringUtils.isBlank(value)){
            value = defaultValue;
        }
        return value.trim();
    }


}
