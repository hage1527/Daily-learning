package com.example.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 根据数据源dataSource02配置sqlSessionFactory02和sqlSession02
 */
@Configuration
@MapperScan(basePackages = "com.example.mapper.shiyanlou02", sqlSessionFactoryRef = "sqlSessionFactory02")
public class SqlSessionConfig02 {

    @Bean("sqlSessionFactory02")
    public SqlSessionFactory getSqlSessionFactory(
            @Qualifier("dataSource02") DataSource dataSource) {
        try {
            // 实例化一个工具类，用来创建 SqlSessionFactory
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(dataSource);
            factoryBean.setMapperLocations(
                    // 设置Mybatis的xml文件位置
                    new PathMatchingResourcePatternResolver()
                            .getResources("classpath:mapper/shiyanlou02/*.xml")
            );
            // 返回创建好的sqlSessionFactory实例
            return factoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 当创建失败时返回null
        return null;
    }

    @Bean("sqlSession02")
    public SqlSessionTemplate getSqlSession
            (@Qualifier("sqlSessionFactory02") SqlSessionFactory sqlSessionFactory) {
        //利用SqlSessionFactory实例构建一个由SpringBoot管理的线程安全的SqlSession
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
