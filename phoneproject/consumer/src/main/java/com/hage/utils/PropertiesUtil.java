package com.hage.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private static Properties properties = null;
    public static Properties getProperties(){
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("kafka.properties");
        properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
       return  properties;
    }
}
