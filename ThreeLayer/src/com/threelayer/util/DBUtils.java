package com.threelayer.util;

import com.threelayer.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
数据库连接工具类
 */
public class DBUtils {
    private static final String URL = "jdbc:mysql://47.105.69.171:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection connection = null;
    public static PreparedStatement pstm = null;
    public static ResultSet rst = null;

    /**
     * 创建数据库连接
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * 关闭所有资源
     *
     * @param connection
     * @param statement
     * @param rst
     */
    public static void closeAll(Connection connection, Statement statement, ResultSet rst) {
        try {
            if (rst != null) rst.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建预编译statement
     *
     * @param sql
     * @param parms
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static PreparedStatement createPreStatement(String sql, Object[] parms) throws SQLException, ClassNotFoundException {
        pstm = getConnection().prepareStatement(sql);
        if (parms!=null)
            for (int i = 0; i < parms.length; i++) {
                pstm.setObject(i + 1, parms[i]);
            }
        return pstm;
    }

    /**
     * 返回查询总数
     *
     * @param sql
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static int getTotalCount(String sql) throws SQLException, ClassNotFoundException {
        int totalCount = 0;
        pstm = createPreStatement(sql, null);
        rst = pstm.executeQuery();
        if (rst.next()) {
            totalCount = rst.getInt(1);
        }
        closeAll(connection, pstm, rst);
        return totalCount;
    }

    public static boolean executeUpdate(String sql, Object[] parms) {
        try {
            pstm = createPreStatement(sql, parms);
            int result = pstm.executeUpdate();
            if (result > 0)
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll(connection, pstm, null);
        }
    }

    public static ResultSet executeQuery(String sql, Object[] parms) {
        try {
            pstm = createPreStatement(sql, parms);
            rst = pstm.executeQuery();
            return rst;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
