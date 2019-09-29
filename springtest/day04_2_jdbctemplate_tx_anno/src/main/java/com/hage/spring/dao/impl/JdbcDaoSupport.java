package com.hage.spring.dao.impl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author: Administrator
 * @date: 2019/9/25
 * Description: 根据datasource创建JdbcTemplate
 */
public class JdbcDaoSupport {

    private @Setter@Getter JdbcTemplate jdbcTemplate;

    private void setDataSource(DataSource dataSource){
        if (jdbcTemplate==null) {
            jdbcTemplate = createJdbcTemplate(dataSource);
        }
    }

    private JdbcTemplate createJdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource );
    }



}
