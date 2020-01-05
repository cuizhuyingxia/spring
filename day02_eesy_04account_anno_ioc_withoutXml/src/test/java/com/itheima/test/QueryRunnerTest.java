package com.itheima.test;

/**
 * @Author Lian Flower
 * @Date 2019/9/14 19:27
 * @Version 1.0
 */

import com.itheima.service.IAccountService;
import config.SpringConfiguration;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试QueryRunnerTest对象是单例还是多例
 */
public class QueryRunnerTest {

	@Test
	public void testQueryRunner() {
		// 1. 使用注解方式，获取Ioc容器										// 参数为使用了@Configuration注解的类的Class对象，也就是配置类
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		// 2. 获取Service对象
		QueryRunner runner1 = applicationContext.getBean("runner", QueryRunner.class);
		QueryRunner runner2 = applicationContext.getBean("runner", QueryRunner.class);

		System.out.println(runner1 == runner2);
	}
}
