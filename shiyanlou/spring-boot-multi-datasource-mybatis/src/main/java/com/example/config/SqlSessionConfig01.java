package com.example.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 根据数据源dataSource01创建sqlSessionFactory01和sqlSession01
 */
@Configuration

@MapperScan(basePackages = "com.example.mapper.shiyanlou01",sqlSessionFactoryRef = "sqlSessionFactory01")
public class SqlSessionConfig01 {

    /**
     * 向容器中实例化sqlSessionFactory01实例
     */
    @Bean("sqlSessionFactory01")
    @Primary
    public SqlSessionFactory getSqlSessionFactory(
            //根据名称从容器中获取实例
            @Qualifier("dataSource01") DataSource dataSource) {
        try {
            // 实例化一个工具类，用来创建SqlSessionFactory
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(dataSource);
            factoryBean.setMapperLocations(
                    // 设置Mybatis的xml文件位置
                    new PathMatchingResourcePatternResolver()
                            .getResources("classpath:mapper/shiyanlou01/*.xml")
            );
            // 返回创建好的sqlSessionFactory实例
            return factoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 当创建失败时返回null
        return null;
    }

    /**
     * 向容器中实例化sqlSession01实例
     */
    @Bean("sqlSession01")
    @Primary
    public SqlSessionTemplate getSqlSession
            (@Qualifier("sqlSessionFactory01") SqlSessionFactory sqlSessionFactory) {
        // 利用SqlSessionFactory实例构建一个由SpringBoot管理的线程安全的SqlSession
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
