package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.dbassit.DBAssit;
import com.itheima.domain.Account;
import com.itheima.handler.impl.BeanHandler;
import com.itheima.utils.C3P0Util;

import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/14 11:02
 * @Version 1.0
 */

/**
 * 账户的持久层实现类
 */
public class AccountDaoImpl implements IAccountDao{

	// 获取执行sql语句的工具类，需要传入连接池作为参数，然后该工具类会自动帮我们获取连接，执行sql语句，封装结果集，以及释放资源
	private DBAssit dbAssit = new DBAssit(C3P0Util.getDataSource());

	public void saveAccount(Account account) {
		dbAssit.update("insert into account(name, money) values(?, ?)", account.getName(), account.getMoney());
	}

	public void deleteAccount(Integer accountId) {
		dbAssit.update("delete from account where id = ?", accountId);
	}

	public void updateAccount(Account account) {
		dbAssit.update("update account set name = ?, money = ? where id = ?", account.getName(), account.getMoney(), account.getId());
	}

	public Account findAccountById(Integer accountId) {
		Account account = dbAssit.queryForObject("select * from account where id = ?", new BeanHandler<Account>(Account.class), accountId);
		return account;
	}

	public List<Account> findAll() {
		List<Account> accounts = dbAssit.query("select * from account", new BeanHandler<Account>(Account.class));
		return accounts;
	}
}
