package com.hage.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * 1.创建命名空间
 * 2.判断表是否存在
 * 3.创建表
 * 4.生成rowkey
 * 5.预分区健的生成
 */
public class HbaseUtil {
    private static Configuration configuration;

    static {
        configuration = HBaseConfiguration.create();
    }

    /*
    创建命名空间
     */
    public static void createNamespace(String ns) throws IOException {
        //获取admin对象
        Connection connection = ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();
        //创建命名空间描述器
        NamespaceDescriptor descriptor = NamespaceDescriptor.create(ns).build();
        //创建命名空间
        admin.createNamespace(descriptor);
        //关闭资源
        admin.close();
        connection.close();
    }

    /**
     * 判断表是否存在
     *
     * @param tableName
     * @return
     * @throws IOException
     */
    public static boolean tableExists(String tableName) throws IOException {
        Connection connection = ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();
        boolean tableExists = admin.tableExists(TableName.valueOf(tableName));
        return tableExists;
    }

    /*
    创建表
     */
    public static void createTable(String tableName,int regions, String... cfs) throws IOException {
        Connection connection = ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();
        //判断表是否存在
        if (tableExists(tableName)) {
            System.out.println("表已存在");
            admin.close();
            connection.close();
            return;
        }
        //创建表描述器
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        for (String cf : cfs) {
            //为每一个列族创建列族描述器
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(cf);
            //添加至列描述器
            hTableDescriptor.addFamily(hColumnDescriptor);
        }
        hTableDescriptor.addCoprocessor("com.hage.coprocessor.MyCoprocessor");
        //创建表
        admin.createTable(hTableDescriptor,getSplitKeys(regions));
        //关闭资源
        admin.close();
        connection.close();
    }

    //预分区健的生成
    //00|,01|,02|,03|,04|,05|
    public static byte[][] getSplitKeys(int regions) {
        //创建分区键二维数据
        byte[][] splitKeys = new byte[regions][];
        DecimalFormat df = new DecimalFormat("00");
        //循环添加分区键
        for (int i = 0; i < regions; i++) {
            splitKeys[i] = Bytes.toBytes(df.format(i) + "|");
        }
        return splitKeys;
    }

    //生成分区号（13712341234，2017-05-02 12:23:55）
    public static String getRowHash(int regions,String caller,String buildTime){
        DecimalFormat df = new DecimalFormat("00");
        //取手机号中间四位
        String phoneMid = caller.substring(3, 7);
        String yearMonth = buildTime.replace("-", "").substring(0, 6);
        int i = (Integer.valueOf(phoneMid) ^ Integer.valueOf(yearMonth)) % (regions+1);
        return  df.format(i);
    }
    //生成rowkey
    //0x_13712341234_2017-05-02 12:23:55_时间戳_13598769876_flag_duration
    public static String getRowKey(String rowHash, String caller, String buildTime, String buildTS, String callee,String flag, String duration) {

        return rowHash + "_"
                + caller + "_"
                + buildTime + "_"
                + buildTS + "_"
                + callee + "_"
                + flag + "_"
                + duration;
    }


}
