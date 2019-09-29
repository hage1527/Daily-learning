package com.hage.mybatis01;

import com.hage.mybatis01.dao.IUserDao;
import com.hage.mybatis01.entity.User;
import com.hage.mybatis01.mybatis.io.Resources;
import com.hage.mybatis01.mybatis.sqlsession.SqlSession;
import com.hage.mybatis01.mybatis.sqlsession.SqlSessionFactory;
import com.hage.mybatis01.mybatis.sqlsession.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

//入门案例
public class test {
    public static void main(String[] args) throws IOException {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        //6.释放资源
        sqlSession.close();
        in.close();

    }

}
