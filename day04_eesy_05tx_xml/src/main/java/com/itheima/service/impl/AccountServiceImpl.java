package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;

import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/14 16:09
 * @Version 1.0
 */

/**
 * 账户的业务层接口的实现类
 *
 * 		在业务层实现对事务的控制
 */
public class AccountServiceImpl implements IAccountService {

	// 使用spring的set方法注入IAccountDao对象
	private IAccountDao accountDao;

	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public Account findAccountById(Integer accountId) {
		Account account = accountDao.findAccountById(accountId);
		return account;
	}

	public void transfer(String sourceName, String targetName, Float money) {
		System.out.println("transfer...");
		// 1 根据名称查询转出账户
		Account sourceAccount = accountDao.findAccountByName(sourceName);
		// 2 根据名称查询转入账户
		Account targetAccount = accountDao.findAccountByName(targetName);
		// 3 转出账户减钱
		sourceAccount.setMoney(sourceAccount.getMoney() - money);
		// 4 转入账户加钱
		targetAccount.setMoney(targetAccount.getMoney() + money);
		// 5 更新转出账户
		accountDao.updateAccount(sourceAccount);
		// 制造一个异常
		//int i = 1 / 0;
		// 6 更新转入账户
		accountDao.updateAccount(targetAccount);
	}
}
