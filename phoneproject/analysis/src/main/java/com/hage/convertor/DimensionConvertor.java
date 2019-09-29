package com.hage.convertor;

import com.hage.Utils.JDBCUtil;
import com.hage.Utils.LRUCache;
import com.hage.kv.ContactDimension;
import com.hage.kv.DateDimension;
import com.hage.kv.base.BaseDimension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DimensionConvertor {

    private LRUCache keyCache = new LRUCache(1000);

    public DimensionConvertor() {

    }

    public int getDimensionID(BaseDimension baseDimension) throws SQLException {
        //根据参数类型的不同获得不同的key值
        String key = getDimensionKey(baseDimension);
        //用key值到缓存中查找id
        if (keyCache.containsKey(key)) {
            return keyCache.get(key);
        }
        //若缓存中没有，去mysql表中去查
        //获取JDBC连接
        Connection connection = JDBCUtil.getInstance();
        //拼接sql语句（查询和插入）
        String[] sqls=getSqls(baseDimension);
        //查询若无，插入新的记录，若有，查出并写入缓存
        int id = exesql(connection,sqls);
        keyCache.put(key, id);
        //返回查询id
        return id;
    }

    private int exesql(Connection connection, String[] sqls) throws SQLException {
        //第一次查询
        PreparedStatement preparedStatement = connection.prepareStatement(sqls[0]);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        //若无则插入
        preparedStatement = connection.prepareStatement(sqls[1]);
        preparedStatement.executeUpdate();
        //插入后重新查询
        preparedStatement = connection.prepareStatement(sqls[0]);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        //若出错则返回0
        return  0;
    }

    /**
     * 根据维度信息获得相应的sql语句
     * @param baseDimension
     * @return 查询和插入的两条sql语句
     */
    private String[] getSqls(BaseDimension baseDimension) {
        String[] sqls = new String[2];
        if (baseDimension instanceof ContactDimension) {
            ContactDimension contact = (ContactDimension) baseDimension;
            String phoneNum = contact.getPhoneNum();
            String name = contact.getName();
            //查询操作语句
            sqls[0] = "SELECT `id` FROM `tb_contacts` WHERE `telephone`=" + "'" + phoneNum + "'";
            //插入操作语句
            sqls[1] = "INSERT INTO `tb_contacts` VALUES (NULL," + "'" + phoneNum + "','" + name + "')";
        }else {
            DateDimension date =(DateDimension) baseDimension;
            int year = Integer.valueOf(date.getYear());
            int month = Integer.valueOf(date.getMonth());
            int day = Integer.valueOf(date.getDay());
            //查询操作语句
            sqls[0] = "SELECT `id` FROM `tb_dimension_date` WHERE `year`=" + year + " AND `month` =" + month + " AND `day` =" + day;
            //插入操作语句
            sqls[1] = "INSERT INTO `tb_dimension_date` VALUES (NULL," + year + "," + month + "," + day + ")";
        }
        return sqls;
    }

    //根据维度信息获取维度描述
    private String getDimensionKey(BaseDimension baseDimension) {
        StringBuffer buffer = new StringBuffer();
        if (baseDimension instanceof ContactDimension) {
            ContactDimension contactDimension = (ContactDimension) baseDimension;
            buffer.append(contactDimension.getPhoneNum());
        } else {
            DateDimension dateDimension = (DateDimension) baseDimension;
            buffer.append(dateDimension.getYear() + dateDimension.getMonth() + dateDimension.getDay());
        }
        return buffer.toString();
    }
}
