package com.hage.mybatis01.mybatis.io;

import java.io.InputStream;

/**
 * 使用类加载器读取配置文件的流
 */
public class Resources {

    /**
     * 根据传入的参数，获取一个文件的字节输入流
     * @param fpath
     * @return
     */
    public static InputStream getResourceAsStream(String fpath) {
          return Resources.class.getClassLoader().getResourceAsStream(fpath);
    }
}
