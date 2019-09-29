package com.hage.consumer;

import com.hage.constant.constant;
import com.hage.utils.HbaseUtil;
import com.hage.utils.PropertiesUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

public class HBaseDAO {
    //配置信息
    private Properties properties;
    //命名空间
    private String nameSpace;
    //表名
    private String tableName;
    //列族
    private String cf;
    //分区数
    private int regions;
    //hbase连接
    private Connection connection;
    //hbase表对象
    private Table table;
    //puts
    private ArrayList<Put> puts;
    //
    private SimpleDateFormat simpleDateFormat;
    //主被叫参数
    private String flag;

    public HBaseDAO() throws IOException {
        //获取配置信息
        properties = PropertiesUtil.getProperties();
        //初始化配置参数
        nameSpace = properties.getProperty("hbase.namespace");
        tableName = properties.getProperty("hbase.table.name");
        regions = Integer.parseInt(properties.getProperty("hbase.regions"));
        cf = properties.getProperty("hbase.cf");
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        connection = ConnectionFactory.createConnection(constant.configuration);
        table = connection.getTable(TableName.valueOf(tableName));
        puts = new ArrayList<>();
        flag = "1";
        //创建命名空间
//        HbaseUtil.createNamespace(nameSpace);
        //创建表
        HbaseUtil.createTable(tableName, regions, cf,"f2");
    }

    //批量提交put
    public void put(String value) throws ParseException, IOException {
        if (value == null) {
            return;
        }

        //value:19879419704,18302820904,2017-03-27 21:10:56,1150
        String[] split = value.split(",");
        //主叫号码
        String call1 = split[0];
        //被叫号码
        String call2 = split[1];
        //通话建立时间
        String buildTime = split[2];
        //通话时长
        String duration = split[3];
        //通话建立时间时间戳形式
        long buildTS = simpleDateFormat.parse(buildTime).getTime();
        //生成rowhash
        String rowHash = HbaseUtil.getRowHash(regions, call1, buildTime);
        //生成rowkey
        String rowKey = HbaseUtil.getRowKey(rowHash, call1, buildTime, buildTS + "", call2,flag, duration);

        //生成put对象
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes("call1"), Bytes.toBytes(call1));
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes("call2"), Bytes.toBytes(call2));
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes("buildTime"), Bytes.toBytes(buildTime));
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes("bulidTs"), Bytes.toBytes(buildTS + ""));
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes("duration"), Bytes.toBytes(duration));
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes("flag"), Bytes.toBytes(flag));

        puts.add(put);
        if (puts.size()>20){
            table.put(puts);
            puts.clear();
        }

    }

    public void close() throws IOException {
        table.put(puts);
        table.close();
        connection.close();
    }

}
