package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: Administrator
 * @date: 2019/9/26
 * Description:
 */
@Configuration
@Import({JdbcConfig.class,TransactionConfig.class})
@ComponentScan("com.hage.spring")
@PropertySource(value = "classpath:jdbcConfig.properties")
@EnableTransactionManagement
public class SpringConfig {
}
