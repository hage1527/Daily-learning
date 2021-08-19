package com.example.util;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 自定义BaseMapper让子接口继承(我们一般直接继承Mapper<T>，但功能不够多)
 * <p>
 * Mapper接口：基本的增、删、改、查方法
 * MySqlMapper：通用Mapper接口,MySql独有的通用方法
 * <p>
 * 注意：一定要加@RegisterMapper，注册接口
 */
@RegisterMapper
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
