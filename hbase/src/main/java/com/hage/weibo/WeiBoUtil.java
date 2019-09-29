package com.hage.weibo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeiBoUtil {
    //获取hbase配置文件
    private static Configuration configuration = HBaseConfiguration.create();

    static {
        configuration.set("hbase.zookeeper.quorum", "hadoop100");
    }

    /**
     * 创建命名空间
     *
     * @param ns：命名空间名称
     * @throws IOException
     */
    public static void createNamespace(String ns) throws IOException {
        //获取admin
        Connection connection = ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();
        //获取命名空间描述器
        NamespaceDescriptor build = NamespaceDescriptor.create(ns).build();
        //构建命名空间
        admin.createNamespace(build);

        admin.close();
        connection.close();
    }

    /**
     * 创建表
     *
     * @param tableName
     * @param versions
     * @param columnFamilys
     * @throws IOException
     */
    public static void createTable(String tableName, int versions, String... columnFamilys) throws IOException {
        //获取admin
        Connection connection = ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();
        //创建表描述器
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        for (String columnFamily : columnFamilys) {
            //为每个列族创建列描述器，并添加至表描述器
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(columnFamily);
            hColumnDescriptor.setMaxVersions(versions);
            hTableDescriptor.addFamily(hColumnDescriptor);
        }
        //创建表
        admin.createTable(hTableDescriptor);

        admin.close();
        connection.close();

    }

    /*发布微博内容
    1.微博内容表中uid+时间戳添加info
    2.到用户关系表中查找rowkey(uid+时间戳)对应的list<fans>
    3.收件箱中为每一个fans在info列族中添加rowkey
     */
    public static void putData(String uid, String columnFamily, String column, String value) throws IOException {
        //1.微博内容表中uid+时间戳添加info
        Connection connection = ConnectionFactory.createConnection(configuration);
        Table contentTable = connection.getTable(TableName.valueOf(Constant.TABLE_CONTENT));
        //uid+当前时间作为rowkey
        long timeMillis = System.currentTimeMillis();
        String rowKey = uid + "_" + timeMillis;
        //封装put
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(value));
        contentTable.put(put);
        //2.到用户关系表中查找rowkey(uid+时间戳)对应的list<fans>
        ArrayList<Put> puts = new ArrayList<>();

        Table releationTable = connection.getTable(TableName.valueOf(Constant.TABLE_RELEATION));
        Table indexTable = connection.getTable(TableName.valueOf(Constant.TABLE_INBOX));
        //用户关系表中查找fans
        Get get = new Get(Bytes.toBytes(rowKey));
        get.addFamily(Bytes.toBytes("fans"));
        Result result = releationTable.get(get);

        for (Cell cell : result.rawCells()) {
            byte[] cloneValue = CellUtil.cloneValue(cell);
            Put inboxPut = new Put(cloneValue);
            inboxPut.addColumn(Bytes.toBytes("info"), Bytes.toBytes(uid), Bytes.toBytes(rowKey));
            puts.add(inboxPut);
        }
        indexTable.put(puts);

        indexTable.close();
        releationTable.close();
        contentTable.close();
        connection.close();
    }

    /*
     * 添加关注用户（多个）
     * 1.在用户关系表中，给当前用户添加attends
     * 2.在用户关系表中，给被关注用户添加fans
     * 3.在收件箱表中，给当前用户添加关注用户最近所发微博的rowkey
     */
    public static void addAttends(String uid, String... attends) throws IOException {
        Connection connection = ConnectionFactory.createConnection(configuration);
        Table releationTable = connection.getTable(TableName.valueOf(Bytes.toBytes(Constant.TABLE_RELEATION)));
        //1.在用户关系表中，给当前用户添加attends
        //2.在用户关系表中，给被关注用户添加fans
        //存放被关注用户的添加对象
        ArrayList<Put> puts = new ArrayList<>();
        Put attendput = new Put(Bytes.toBytes(uid));
        puts.add(attendput);
        for (String attend : attends) {
            attendput.addColumn(Bytes.toBytes("attends"), Bytes.toBytes(attend), Bytes.toBytes(" "));
            //被关注用户添加粉丝
            Put fanPut = new Put(Bytes.toBytes(attend));
            fanPut.addColumn(Bytes.toBytes("fans"), Bytes.toBytes(uid), Bytes.toBytes(" "));
            puts.add(fanPut);
        }
        releationTable.put(puts);
        //3.在收件箱表中，给当前用户添加关注用户最近所发微博的rowkey
        Table inboxTable = connection.getTable(TableName.valueOf(Constant.TABLE_INBOX));
        Table contentTable = connection.getTable(TableName.valueOf(Constant.TABLE_CONTENT));

        Put inboxPut = new Put(Bytes.toBytes(uid));
        if (attends.length < 0) return;
        for (String attend : attends) {
            //通过startRow和stopRow构建扫描器
//            Scan scan = new Scan(Bytes.toBytes(attend), Bytes.toBytes(attend + "|"));
            //扫描过滤器构建扫描器
            Scan scan = new Scan();
            scan.setFilter(new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(attend + "_")));
            //通过扫描器得到内容：rowkey包含attend
            ResultScanner scanner = contentTable.getScanner(scan);
            for (Result result : scanner) {
                byte[] row = result.getRow();
                inboxPut.addColumn(Bytes.toBytes("info"), Bytes.toBytes(attend), row);
                inboxTable.put(inboxPut);
            }
        }

        inboxTable.close();
        contentTable.close();
        releationTable.close();
        connection.close();
    }

    /**
     * 取关
     * 1.在用户关系表中，删除当前用户的attends
     * 2.在用户关系表中，删除被取关用户的fans（操作者）
     * 3.在收件箱表中删除取关用户的所有数据
     */
    public static void deleteAttends(String uid, String... deletes) throws IOException {
        Connection connection = ConnectionFactory.createConnection(configuration);
        //获取用户关系表对象
        Table releationTable = connection.getTable(TableName.valueOf(Constant.TABLE_RELEATION));
        //存放用户关系表删除delete对象
        ArrayList<Delete> deleteArrayList = new ArrayList<>();
        Delete deleteAttend = new Delete(Bytes.toBytes(uid));
        //遍历删除deletes
        for (String delete : deletes) {
            deleteAttend.addColumn(Bytes.toBytes("attends"), Bytes.toBytes(delete));
            //被关注用户删除fans
            Delete deleteFans = new Delete(Bytes.toBytes(delete));
            deleteFans.addColumn(Bytes.toBytes("fans"), Bytes.toBytes(uid));
            deleteArrayList.add(deleteFans);
        }
        deleteArrayList.add(deleteAttend);
        releationTable.delete(deleteArrayList);
        //收件箱删除uid下关注deletes内容
        Table inboxTable = connection.getTable(TableName.valueOf(Constant.TABLE_INBOX));
        Delete deleteInbox = new Delete(Bytes.toBytes(uid));
        for (String delete : deletes) {
            deleteInbox.addColumn(Bytes.toBytes("info"), Bytes.toBytes(delete));
        }
        inboxTable.delete(deleteInbox);

        inboxTable.close();
        releationTable.close();
        connection.close();
    }

    /*
    获取关注的人的微博内容
     */
    public static void getWeibo(String uid) throws IOException {
        Connection connection = ConnectionFactory.createConnection(configuration);
        //获取收件箱表和用户内容表
        Table contentTable = connection.getTable(TableName.valueOf(Constant.TABLE_CONTENT));
        Table inboxTable = connection.getTable(TableName.valueOf(Constant.TABLE_INBOX));
        //到收件箱表中查找uid对应的attends
        Get get = new Get(Bytes.toBytes(uid));
        get.setMaxVersions(3);
        Result result = inboxTable.get(get);
        for (Cell cell : result.rawCells()) {
            byte[] bytes = CellUtil.cloneValue(cell);
            Get getContent = new Get(bytes);
            getContent.addFamily(Bytes.toBytes("info"));
            Result resultContent = contentTable.get(getContent);
            for (Cell rawCell : resultContent.rawCells()) {
                String id_ts = Bytes.toString(CellUtil.cloneRow(rawCell));
                String id = id_ts.split("_")[0];
                String ts = id_ts.split("_")[1];
                String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date(Long.parseLong(ts)));
                System.out.println("用户：" + id + "，时间" + date + "，内容：" + Bytes.toString(CellUtil.cloneValue(rawCell)));
            }
        }
        inboxTable.close();
        contentTable.close();
        connection.close();
    }

}
