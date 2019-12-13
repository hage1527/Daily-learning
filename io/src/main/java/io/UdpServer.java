package io;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author: Administrator
 * @date: 2019/12/13
 * Description: udp接收数据
 */
public class UdpServer {
    public static void main(String[] args) throws IOException {
        // 依据监听端口号创建数据报套接字
        DatagramSocket datagramSocket = new DatagramSocket(10000);
        // 创建数据报
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        // socket接收数据
        datagramSocket.receive(datagramPacket);
        System.out.println(datagramPacket.getData().toString());
        datagramSocket.close();
    }
}
