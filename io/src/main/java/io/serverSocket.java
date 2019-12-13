package io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author: Administrator
 * @date: 2019/12/13
 * Description: 服务端监听
 */
public class serverSocket {
    public static void main(String[] args) throws IOException {
        // 服务端socket
        ServerSocket socket = new ServerSocket(9999);
        Socket accept = socket.accept();
        // 根据socket获取输入流
        InputStream inputStream = accept.getInputStream();
        byte[] bytes = new byte[1024];
        // 从输入流中读取字节数组
        int read = inputStream.read(bytes);
        String str = new String(bytes, 0, read);
        System.out.println(str);

    }
}
