package com.itheima.jdbcTemplate;

/**
 * @Author Lian Flower
 * @Date 2019/9/16 13:18
 * @Version 1.0
 */

import com.itheima.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

/**
 * JdbcTemplate的基本使用
 */
public class JdbcTemplateDemo1 {

	public static void main(String[] args) {
		// 1. 创建容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
		// 2. 获取JdbcTemplate
		JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
		// 3. 执行操作
		/*// 查询所有账户
		List<Account> accounts = jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<Account>(Account.class));
		for (Account account : accounts) {
			System.out.println(account);
		}*/
		/*// 根据id查询账户
		Account account = jdbcTemplate.queryForObject("select * from account where id = ?", new BeanPropertyRowMapper<Account>(Account.class), 15);
		System.out.println(account);*/
		/*// 查询总记录数
		Integer totalCount = jdbcTemplate.queryForObject("select count(*) from account", Integer.class);
		System.out.println(totalCount);*/
		// 保存账户
		//jdbcTemplate.update("insert into account(name, money) values(?, ?)", "中秋节", 815f);
		// 更新账户
		//jdbcTemplate.update("update account set money = ? where id = ?", 3000f, 15);
		// 根据id删除账户
		jdbcTemplate.update("delete from account where id = ?", 15);


	}
}
