package com.hage.mybatis01;

import com.hage.mybatis01.dao.IUserDao;
import com.hage.mybatis01.entity.QueryVo;
import com.hage.mybatis01.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//入门案例
public class test {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    /**
     * 初始资源配置
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
         sqlSession = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
         userDao = sqlSession.getMapper(IUserDao.class);
    }

    /**
     * 关闭资源
     * @throws IOException
     */
    @After
    public void destory() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    @Test
    public void findAll() throws IOException {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }


    @Test
    public void findById(){
        Integer id = 51;
        User user = userDao.findById(id);
        System.out.println(user);
    }
    @Test
    public void findByName(){

        List<User> users = userDao.findByName("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void findUserByCondition(){
        User user = new User();
//        user.setUserName("老王");
        user.setUserSex("女");
        List<User> userByCondition = userDao.findUserByCondition(user);
        for (User user1 : userByCondition) {
            System.out.println(user1);
        }
    }

    @Test
    public void findUserInIds(){
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(41);
        integers.add(42);
        integers.add(48);
        QueryVo queryVo = new QueryVo();
        queryVo.setIds(integers);
        List<User> user = userDao.findUserInIds(queryVo);
        for (User user1 : user) {
            System.out.println(user1);
        }
    }



}
