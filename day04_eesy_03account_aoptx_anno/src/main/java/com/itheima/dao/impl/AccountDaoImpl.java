package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

	@Autowired
	private QueryRunner queryRunner;

	@Autowired
	private ConnectionUtils connectionUtils;

	public List<Account> findAllAccount() {
		try {
			List<Account> accounts = queryRunner.query(connectionUtils.getThreadConnection(), "select * from account", new BeanListHandler<Account>(Account.class));
			return accounts;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public Account findAccountById(Integer accountId) {
		try {
			Account account = queryRunner.query(connectionUtils.getThreadConnection(), "select * from account where id = ?", new BeanHandler<Account>(Account.class), accountId);
			return account;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void updateAccount(Account account) {
		try {
			queryRunner.update(connectionUtils.getThreadConnection(), "update account set name = ?, money = ? where id = ?", account.getName(), account.getMoney(), account.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void saveAccount(Account account) {
		try {
			queryRunner.update(connectionUtils.getThreadConnection(), "insert into account(name, money) values(?, ?)", account.getName(), account.getMoney());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteAccount(Integer accountId) {
		try {
			queryRunner.update(connectionUtils.getThreadConnection(), "delete from account where id = ?", accountId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Account findAccountByName(String accountName) {
		try {
			List<Account> accounts = queryRunner.query(connectionUtils.getThreadConnection(), "select * from account where name = ?", new BeanListHandler<Account>(Account.class), accountName);
			if (accounts != null && accounts.size() == 0) {
				return null;
			}
			if (accounts.size() > 1) {
				throw new RuntimeException("结果集不唯一，数据有问题");
			}
			return accounts.get(0);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
