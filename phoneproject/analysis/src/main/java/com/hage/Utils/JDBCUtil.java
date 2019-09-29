package com.hage.Utils;

import java.sql.*;

public class JDBCUtil {
    private static Connection connection = null;

    private static final  String MYSQL_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final  String MYSQL_URL = "jdbc:mysql://hadoop100:3306/ct?useUnicode=true&characterEncoding=UTF-8";
    private static final  String MYSQL_USERNAME = "root";
    private static final  String MYSQL_PASSWORD = "000000";

    private static Connection getConnection(){
        try {
            Class.forName(MYSQL_DRIVER_CLASS);
            return DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
        } catch (ClassNotFoundException|SQLException  e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
    获得一个连接
     */
    public static Connection getInstance(){
        if (connection==null)
            connection=getConnection();
        return connection;
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if (connection!=null) {
            connection.close();
        }
        if (statement!=null){
            statement.close();
        }
        if (resultSet!=null){
            resultSet.close();
        }
    }

}
