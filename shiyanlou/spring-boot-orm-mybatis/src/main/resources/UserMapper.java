package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author: Administrator
 * @date: 2020/10/31
 * Description:
 */
@Mapper
public interface UserMapper {
    @Select("select id,name,age from user")
    List<User> findAll();

    @Select("select id,name,age from user where id = #{id}")
    User findById(Integer id);

    @Update("insert into user (id,name,age) values (#{id},#{name},#{age})")
    Integer add(User user);

    @Update("update user set name = #{name} where id = #{id}")
    Integer update(Integer id, String name);

    @Update("delete from user where id = #{id}")
    Integer delete(Integer id);


}
