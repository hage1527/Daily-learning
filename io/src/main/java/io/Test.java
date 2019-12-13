package io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: Administrator
 * @date: 2019/12/13
 * Description:
 */
public class Test {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
        System.out.println(inetAddress);
        System.out.println(inetAddress.getAddress());
        System.out.println(inetAddress.getHostName());

        System.out.println(InetAddress.getLocalHost());

    }
}
