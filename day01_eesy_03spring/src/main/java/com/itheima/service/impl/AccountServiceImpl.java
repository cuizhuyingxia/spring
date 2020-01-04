package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author Lian Flower
 * @Date 2019/9/12 12:50
 * @Version 1.0
 */

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

	private IAccountDao accountDao = new AccountDaoImpl();


	public AccountServiceImpl() {
		System.out.println("对象被创建了");
	}

	/**
	 * 模拟保存账户
	 */
	public void saveAccount() {
		accountDao.saveAccount();
	}
}
