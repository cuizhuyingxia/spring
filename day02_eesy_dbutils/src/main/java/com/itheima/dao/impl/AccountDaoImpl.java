package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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

	// 获取QueryRunner对象，需要传入连接池作为参数，然后它会自动帮我们获取连接，执行sql语句，封装结果集，以及释放资源
	private QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());

	public void saveAccount(Account account) {
		try {
			queryRunner.update("insert into account(name, money) values(?, ?)", account.getName(), account.getMoney());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteAccount(Integer accountId) {
		try {
			queryRunner.update("delete from account where id = ?", accountId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void updateAccount(Account account) {
		try {
			queryRunner.update("update account set name = ?, money = ? where id = ?", account.getName(), account.getMoney(), account.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Account findAccountById(Integer accountId) {
		Account account = null;
		try {
			account = queryRunner.query("select * from account where id = ?", new BeanHandler<Account>(Account.class), accountId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return account;
	}

	public List<Account> findAll() {
		List<Account> accounts = null;
		try {
			accounts = queryRunner.query("select * from account", new BeanListHandler<Account>(Account.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return accounts;
	}
}
