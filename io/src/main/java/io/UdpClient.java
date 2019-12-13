package io;

import java.io.IOException;
import java.net.*;
import java.nio.IntBuffer;

/**
 * @author: Administrator
 * @date: 2019/12/13
 * Description: udp发送数据报
 */
public class UdpClient {
    public static void main(String[] args) throws IOException {
        // 创建数据报套接字
        DatagramSocket datagramSocket = new DatagramSocket();
        // 创建数据报包括连接ip和端口号
        byte[] bytes = "hello i am udp".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length,InetAddress.getByName("127.0.0.1"),10000);
        // socket发送数据
        datagramSocket.send(datagramPacket);
        datagramSocket.close();
        final IntBuffer allocate = IntBuffer.allocate(1024);

    }
}
