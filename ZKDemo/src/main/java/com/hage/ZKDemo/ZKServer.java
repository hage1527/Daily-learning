package com.hage.ZKDemo;

import org.apache.zookeeper.*;

import java.io.IOException;

public class ZKServer {

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

            }
        });
    }

    /**
     * 向zookeeper注册即在/server节点下新建临时顺序编号目录节点并在节点中写入自己的主机名
     *
     * @param hostname：主机名
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void registServer(String hostname) throws KeeperException, InterruptedException {
        String create = zooKeeper.create(parentNode + "/server",
                hostname.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname + " is online " + create);
    }

    /**
     * 业务功能
     *
     * @param hostname
     * @throws InterruptedException
     */
    public void business(String hostname) throws InterruptedException {
        System.out.println(hostname + " is working");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws Exception {
        ZKServer zkServer = new ZKServer();
        zkServer.getClient();
        zkServer.registServer(args[0]);
        zkServer.business(args[0]);
    }


}
