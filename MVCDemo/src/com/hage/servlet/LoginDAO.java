package com.hage.servlet;

import java.sql.*;

public class LoginDAO {
    public static int login(Login login){
        String url = "jdbc:mysql://47.105.69.171:3306/test";
        String user = "root";
        String password = "root";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            String sql = "select * from login where name = ? and pwd = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login.getName());
            preparedStatement.setString(2, login.getPassword());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return 1;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }finally {
            try {
                if (resultSet!=null) resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  0;
    }
}
