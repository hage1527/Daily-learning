package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

/**
 * @author: Administrator
 * @date: 2019/9/26
 * Description:
 */
public class TransactionConfig {
    /**
     * 创建事务管理器
     * @param dataSource
     * @return
     */
    @Bean("transactionManager")
    public PlatformTransactionManager createTransactionManager(@Autowired  DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
