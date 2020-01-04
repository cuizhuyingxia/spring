package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.factory.BeanFactory;
import com.itheima.service.IAccountService;

/**
 * @Author Lian Flower
 * @Date 2019/9/12 12:50
 * @Version 1.0
 */

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {
	//private IAccountDao accountDao = new AccountDaoImpl();
	private IAccountDao accountDao = (IAccountDao)BeanFactory.getBean("accountDao");

	/**
	 * 模拟保存账户
	 */
	public void saveAccount() {
		int i = 1;
		accountDao.saveAccount();
		System.out.println(i);
		i++;
	}
}
