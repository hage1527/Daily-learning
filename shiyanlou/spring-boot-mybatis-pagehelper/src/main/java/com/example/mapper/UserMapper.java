package com.example.mapper;

import com.example.entity.User;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface UserMapper extends Mapper<User> , MySqlMapper<User> {
}
