package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * @author: Administrator
 * @date: 2020/10/31
 * Description:
 */
@Repository
public interface UserMapper {

    List<User> findAll();

    User findById(Integer id);

    Integer insert(User user);

    Integer update(@Param("id") Integer id, @Param("name") String name);

    Integer delete(Integer id);


}
