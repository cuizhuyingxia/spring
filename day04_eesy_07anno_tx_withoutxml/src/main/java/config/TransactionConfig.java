package config;

/**
 * @Author Lian Flower
 * @Date 2019/9/16 20:23
 * @Version 1.0
 */

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 和事务相关的配置类
 */
public class TransactionConfig {

	/**
	 * 创建事务管理器对象
	 * @param dataSource
	 * @return	PlatformTransactionManager是DataSourceTransactionManager的接口
	 */
	@Bean("transactionManager")
	public PlatformTransactionManager createTransaction(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
