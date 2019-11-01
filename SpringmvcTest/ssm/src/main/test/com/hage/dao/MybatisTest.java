package com.hage.dao;

import com.hage.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author: Administrator
 * @date: 2019/10/16
 * Description: mybatis测试
 */
public class MybatisTest {
    @Test
    public void findALL() throws IOException {
       //加载配置文件
        Reader in = Resources.getResourceAsReader("SqlMapConfig.xml");
        //创建sqlSessionBuilder对象
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        //创建sqlSessionFactory
        SqlSessionFactory factory = factoryBuilder.build(in);
        //创建sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //创建代理对象
        IAccountDao accountDao = sqlSession.getMapper(IAccountDao.class);
        //查询所有
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}
