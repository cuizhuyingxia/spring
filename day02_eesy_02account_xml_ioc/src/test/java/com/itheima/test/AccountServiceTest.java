package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/14 17:01
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

	@Autowired
	private IAccountService accountService;

	@Test
	public void testFindAll() {
		// 3. 执行方法
		List<Account> accounts = accountService.findAllAccount();
		for (Account account : accounts) {
			System.out.println(account);
		}
	}

	@Test
	public void testFindOne() {
		// 1. 获取Ioc容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
		// 2. 获取Service对象
		IAccountService accountService = applicationContext.getBean("accountService", IAccountService.class);
		// 3. 执行方法
		Account account = accountService.findAccountById(3);
		System.out.println(account);
	}

	@Test
	public void testSave() {
		// 1. 获取Ioc容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
		// 2. 获取Service对象
		IAccountService accountService = applicationContext.getBean("accountService", IAccountService.class);
		// 3. 执行方法
		Account account = new Account();
		account.setName("mumu");
		account.setMoney(3000f);
		accountService.saveAccount(account);
	}

	@Test
	public void testUpdate() {
		// 1. 获取Ioc容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
		// 2. 获取Service对象
		IAccountService accountService = applicationContext.getBean("accountService", IAccountService.class);
		// 3. 执行方法
		Account account = new Account();
		account.setId(1);
		account.setName("tutu");
		account.setMoney(5000f);
		accountService.updateAccount(account);
	}

	@Test
	public void testDelete() {
		// 1. 获取Ioc容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
		// 2. 获取Service对象
		IAccountService accountService = applicationContext.getBean("accountService", IAccountService.class);
		// 3. 执行方法
		accountService.deleteAccount(3);
	}
}
