package com.itheima.jdbcTemplate;

/**
 * @Author Lian Flower
 * @Date 2019/9/16 13:18
 * @Version 1.0
 */

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcTemplate的基本使用
 */
public class JdbcTemplateDemo4 {

	public static void main(String[] args) {
		// 1. 创建容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
		// 2. 获取JdbcTemplate
		IAccountDao accountDao = applicationContext.getBean("accountDao", IAccountDao.class);
		// 3. 执行操作
		//Account account = accountDao.findAccountByName("coco");
		//System.out.println(account);
		Account account = new Account();
		account.setId(26);
		account.setName("莲花");
		account.setMoney(7000f);
		accountDao.updateAccount(account);

	}
}
