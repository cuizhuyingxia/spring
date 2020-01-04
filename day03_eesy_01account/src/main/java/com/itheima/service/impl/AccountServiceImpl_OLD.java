package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;

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
public class AccountServiceImpl_OLD implements IAccountService {

	// 使用spring的set方法注入IAccountDao对象
	private IAccountDao accountDao;

	private TransactionManager transactionManager;

	public void setTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public List<Account> findAllAccount() {
		try {
			// 1. 开启事务
			transactionManager.beginTransaction();
			// 2. 执行操作
			List<Account> accounts = accountDao.findAllAccount();
			// 3. 提交事务
			transactionManager.commitTransaction();
			// 4. 返回结果
			return accounts;
		} catch (Exception e) {
			// 5. 回滚事务
			transactionManager.rollbackTransaction();
			throw new RuntimeException(e);
		} finally {
			// 6. 释放连接
			transactionManager.release();
		}
	}

	public Account findAccountById(Integer accountId) {
		try {
			// 1. 开启事务
			transactionManager.beginTransaction();
			// 2. 执行操作
			Account account = accountDao.findAccountById(accountId);
			// 3. 提交事务
			transactionManager.commitTransaction();
			// 4. 返回结果
			return account;
		} catch (Exception e) {
			// 5. 回滚事务
			transactionManager.rollbackTransaction();
			throw new RuntimeException(e);
		} finally {
			// 6. 释放连接
			transactionManager.release();
		}
	}

	public void updateAccount(Account account) {
		try {
			// 1. 开启事务
			transactionManager.beginTransaction();
			// 2. 执行操作
			accountDao.updateAccount(account);
			// 3. 提交事务
			transactionManager.commitTransaction();
		} catch (Exception e) {
			// 4. 回滚事务
			transactionManager.rollbackTransaction();
		} finally {
			// 5. 释放连接
			transactionManager.release();
		}

	}

	public void saveAccount(Account account) {
		try {
			// 1. 开启事务
			transactionManager.beginTransaction();
			// 2. 执行操作
			accountDao.saveAccount(account);
			// 3. 提交事务
			transactionManager.commitTransaction();
		} catch (Exception e) {
			// 4. 回滚事务
			transactionManager.rollbackTransaction();
		} finally {
			// 5. 释放连接
			transactionManager.release();
		}
	}

	public void deleteAccount(Integer accountId) {
		try {
			// 1. 开启事务
			transactionManager.beginTransaction();
			// 2. 执行操作
			accountDao.deleteAccount(accountId);
			// 3. 提交事务
			transactionManager.commitTransaction();
		} catch (Exception e) {
			// 4. 回滚事务
			transactionManager.rollbackTransaction();
		} finally {
			// 5. 释放连接
			transactionManager.release();
		}

	}

	public void transfer(String sourceName, String targetName, Float money) {
		try {
			// 1. 开启事务
			transactionManager.beginTransaction();
			// 2. 执行操作
			// 2.1 根据名称查询转出账户
			Account sourceAccount = accountDao.findAccountByName(sourceName);
			// 2.2 根据名称查询转入账户
			Account targetAccount = accountDao.findAccountByName(targetName);
			// 2.3 转出账户减钱
			sourceAccount.setMoney(sourceAccount.getMoney() - money);
			// 2.4 转入账户加钱
			targetAccount.setMoney(targetAccount.getMoney() + money);
			// 2.5 更新转出账户
			accountDao.updateAccount(sourceAccount);
			// 制造一个异常
			int i = 1 / 0;
			// 2.6 更新转入账户
			accountDao.updateAccount(targetAccount);
			// 3. 提交事务
			transactionManager.commitTransaction();
		} catch (Exception e) {
			// 4. 回滚事务
			transactionManager.rollbackTransaction();
			e.printStackTrace();
		} finally {
			// 5. 释放连接
			transactionManager.release();
		}

	}
}
