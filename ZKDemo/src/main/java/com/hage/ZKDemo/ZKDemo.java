package com.hage.ZKDemo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ZKDemo {

    private String connectString = "hadoop100:2181,hadoop101:2181,hadoop101:2181";
    private int sessionTimeOut = 2000;
    private ZooKeeper zooKeeper = null;

    /**
     * 获取zookeeper客户端
     * @throws IOException
     */
    @Before
    public void getClient() throws IOException {
        zooKeeper = new ZooKeeper(connectString, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                //接收到zookeeper发来的通知以后，做出的处理措施（自己的处理的业务逻辑）
                System.out.println(watchedEvent.getType() +"---"+ watchedEvent.getPath());
                //再次启动监听
//                try {
//                    List<String> children = zooKeeper.getChildren("/", true);
//                    for (String child:children){
//                        System.out.println(child);
//                    }
//                } catch (KeeperException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    /**
     * 创建节点
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void testCreate() throws KeeperException, InterruptedException {
        zooKeeper.create("/hage",
                "hage".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,//权限
                CreateMode.PERSISTENT);//节点的类型

    }

    /**
     * 判断节点是否存在
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void testExit() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists("/hage", true);
        System.out.println(stat == null ? "Notexit":"exit");
    }
    //获取子节点信息
    @Test
    public void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/", true);
        for (String child:children){
            System.out.println(child);
        }
        //延时阻塞
        Thread.sleep(Long.MAX_VALUE);
    }
    //获取节点数据并检测
    @Test
    public void testGet() throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData("/hage", true, null);
        System.out.println(new String(data));
        //延时阻塞
        Thread.sleep(Long.MAX_VALUE);
    }
    //改变节点数据
    @Test
    public void testSet() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.setData("/hage",
                "you gai le ".getBytes(), -1);
        System.out.println(stat == null ? "failure":"sucsess");
    }

}
