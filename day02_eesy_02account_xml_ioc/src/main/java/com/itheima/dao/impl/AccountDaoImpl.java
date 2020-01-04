package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/14 16:11
 * @Version 1.0
 */

/**
 * 账户持久层接口的实现类
 */
public class AccountDaoImpl implements IAccountDao {

	// 使用spring的set方法注入QueryRunner对象
	private QueryRunner queryRunner;

	public void setQueryRunner(QueryRunner queryRunner) {
		this.queryRunner = queryRunner;
	}

	public List<Account> findAllAccount() {
		List<Account> accounts = null;
		try {
			accounts = queryRunner.query("select * from account", new BeanListHandler<Account>(Account.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return accounts;
	}

	public Account findAccountById(Integer accountId) {
		Account account = null;
		try {
			account = queryRunner.query("select * from account where id = ?", new BeanHandler<Account>(Account.class), accountId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return account;
	}

	public void updateAccount(Account account) {
		try {
			queryRunner.update("update account set name = ?, money = ? where id = ?", account.getName(), account.getMoney(), account.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void saveAccount(Account account) {
		try {
			queryRunner.update("insert into account(name, money) values(?, ?)", account.getName(), account.getMoney());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteAccount(Integer accountId) {
		try {
			queryRunner.update("delete from account where id = ?", accountId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
