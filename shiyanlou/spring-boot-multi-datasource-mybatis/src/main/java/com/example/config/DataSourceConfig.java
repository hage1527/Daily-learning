package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 根据配置文件中的属性值配置两个数据源dataSource01和dataSource02
 */
@Configuration
public class DataSourceConfig {

    /**
     * 配置数据源 dataSource01
     * @return
     */
    @Bean("dataSource01")
    // 设置该数据源为默认数据源
    @Primary
    // 以spring.datasource.shiyanlou01为前缀的属性值自动绑定到对应的字段中
    @ConfigurationProperties(prefix = "spring.datasource.shiyanlou01")
    public DataSource getDataSource01() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置数据源 dataSource02
     * @return
     */
    @Bean("dataSource02")
    @ConfigurationProperties(prefix = "spring.datasource.shiyanlou02")
    public DataSource getDataSource02() {
        return DataSourceBuilder.create().build();
    }
}
