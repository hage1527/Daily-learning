package com.hage.ZKDemo;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZKClient {

    private ZooKeeper zooKeeper = null;
    private String connectString = "hadoop100:2181,hadoop101:2181,hadoop102:2181";
    private static int sessionTimeout = 2000;
    private String parentNode = "/servers";

    /**
     * 获取zookeeper客户端
     *
     * @throws IOException
     */
    public void getClient() throws IOException {
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    getServerList();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 获取在线的服务器主机名列表，并不断监听
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void getServerList() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren(parentNode, true);
        ArrayList<String> hosts = new ArrayList<>();
        for (String child:children){
            byte[] data = zooKeeper.getData(parentNode + "/" + child, false, null);
            hosts.add(new String(data));
        }
        System.out.println(hosts);

    }

    /**
     * 业务功能
     * @throws InterruptedException
     */
    public void business() throws InterruptedException {
        System.out.println("client is working");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws Exception {
        ZKClient zkClient = new ZKClient();
        zkClient.getClient();
        zkClient.getServerList();
        zkClient.business();

    }


}
