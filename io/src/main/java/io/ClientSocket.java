package io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;


/**
 * @author: Administrator
 * @date: 2019/12/13
 * Description: 客户端
 */
public class ClientSocket {
    public static void main(String[] args) throws IOException {
        // 客户端socket
        Socket socket = new Socket(InetAddress.getLocalHost(), 10000);
        // 根据socket获取输出流
        OutputStream outputStream = socket.getOutputStream();
        // 输出流写数据
        outputStream.write("hello".getBytes());
        socket.close();

    }
}
