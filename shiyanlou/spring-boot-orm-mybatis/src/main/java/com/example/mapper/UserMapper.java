package com.example.mapper;

import com.example.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    List<User> findAll();

    User findById(Integer id);

    Integer insert(User user);

    Integer update(Integer id, String name);

    Integer delete(Integer id);


}
