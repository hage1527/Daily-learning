package com.hage.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestHbase {
    private static Connection connection = null;
    private static Admin admin = null;

    static {
        //1.获取配置信息
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hadoop100");
        try {
            //2.获取连接
            connection = ConnectionFactory.createConnection(configuration);
            //3.获取hbaseadmin对象
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isTableExist(String tableName) throws IOException {

        return admin.tableExists(TableName.valueOf(tableName));
    }

    private static void deleteTable(String tableName) throws IOException {
        if (!isTableExist(tableName)) {
            System.out.println("表不存在");
            return;
        }
        admin.disableTable(TableName.valueOf(tableName));
        admin.deleteTable(TableName.valueOf(tableName));
    }

    private static void createTable(String tableName, List<String> columnFamily) throws IOException {
        if (isTableExist(tableName)) {
            System.out.println("表已存在");
            return;
        }
        //创建表描述器
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        //创建列族描述器
        for (String column : columnFamily) {
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(column);
            //表描述器中添加列族描述器
            hTableDescriptor.addFamily(hColumnDescriptor);
        }
        try {
            admin.createTable(hTableDescriptor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void close(Connection connection, Admin admin) {
        if (connection != null) {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (admin != null) {
            try {
                admin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 添加一条数据
     *
     * @param tableName
     * @param rowKey
     * @param cf：列族
     * @param cl：列名
     * @param value
     */
    private static void putData(String tableName, String rowKey, String cf, String cl, String value) {
        try {
            //获取表对象
            Table table = connection.getTable(TableName.valueOf(tableName));
            //创建put对象
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cl), Bytes.toBytes(value));
            table.put(put);
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getData(String tableName, String rowKey) throws IOException {
        //获取表对象
        Table table = connection.getTable(TableName.valueOf(tableName));
        //创建get对象
        Get get = new Get(Bytes.toBytes(rowKey));
//        get.addFamily(Bytes.toBytes("f1"));
        Result result = table.get(get);
        Cell[] cells = result.rawCells();
        //遍历打印数据
        for (Cell cell : cells) {
            System.out.println(Bytes.toString(CellUtil.cloneRow(cell)));
            System.out.println(Bytes.toString(CellUtil.cloneFamily(cell)));
            System.out.println(Bytes.toString(CellUtil.cloneQualifier(cell)));
            System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
        }
        table.close();
    }

    //获取一行数据（指定列族：列）
    private static void getDataByCN(String tableName, String rowkey, String cf, String cn) throws IOException {

        Table table = connection.getTable(TableName.valueOf(tableName));

        Get get = new Get(Bytes.toBytes(rowkey));
//        get.addFamily()//获取一个列族下的所有数据
        get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));

        Result result = table.get(get);
        Cell[] cells = result.rawCells();
        //打印获得的数据
        for (Cell cell : cells) {
            System.out.println("rowkey:" + Bytes.toString(CellUtil.cloneRow(cell))
                    + ",CF:" + Bytes.toString(CellUtil.cloneFamily(cell))
                    + ",CN:" + Bytes.toString(CellUtil.cloneQualifier(cell))
                    + ",VALUE:" + Bytes.toString(CellUtil.cloneValue(cell)));
        }

        table.close();
    }

    private static void scan(String tableName) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));

        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            Cell[] cells = result.rawCells();
            //打印获得的数据
            for (Cell cell : cells) {
                System.out.println("rowkey:" + Bytes.toString(CellUtil.cloneRow(cell))
                        + ",CF:" + Bytes.toString(CellUtil.cloneFamily(cell))
                        + ",CN:" + Bytes.toString(CellUtil.cloneQualifier(cell))
                        + ",VALUE:" + Bytes.toString(CellUtil.cloneValue(cell)));
            }

        }
        table.close();
    }

    /**
     * 删除指定列的一行数据
     *
     * @param tableName
     * @param rowKey
     * @param cf
     * @param cn
     * @throws IOException
     */
    private static void deleteData(String tableName, String rowKey, String cf, String cn) throws IOException {

        Table table = connection.getTable(TableName.valueOf(tableName));

        Delete delete = new Delete(Bytes.toBytes(rowKey));
        //给Delete对象添加具体的列族：列
        delete.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));

        table.delete(delete);
        table.close();
    }

    //删除多条数据
    private static void deleteDatas(String tableName, String... rowKeys) throws IOException {

        Table table = connection.getTable(TableName.valueOf(tableName));

        ArrayList<Delete> deletes = new ArrayList<>();

        for (String rowKey : rowKeys) {
            Delete delete = new Delete(Bytes.toBytes(rowKey));
            deletes.add(delete);
        }

        table.delete(deletes);

        table.close();
    }


    public static void main(String[] args) throws IOException {
        //创建一个表
//        ArrayList<String> columnFamily = new ArrayList<String>();
//        columnFamily.add("f1");
//        columnFamily.add("f2");
//        String tableName = "hage";
//        System.out.println(isTableExist(tableName));
//        createTable(tableName, columnFamily);
//        System.out.println(isTableExist(tableName));

        //添加一条数据
//        putData("hage", "1003", "f1", "name", "sunbo");
//        putData("hage", "1004", "f1", "name", "sunbowen");

        //根据rowkey获取一条数据
//        getData("hage", "1001");

        //全局扫描表
        scan("hage");

        //删除指定列的一行数据
//        deleteData("hage", "1001", "f1", "name");

        close(connection, admin);

    }
}
