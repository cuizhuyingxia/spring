package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/16 15:28
 * @Version 1.0
 */

/**
 * 账户的持久层实现类
 */
public class AccountDaoImpl extends JdbcDaoSupport implements IAccountDao {

	public Account findAccountById(Integer accountId) {
		String sql = "select * from account where id = ?";
		Account account = super.getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<Account>(Account.class), accountId);
		return account;
	}

	public Account findAccountByName(String accountName) {
		String sql = "select * from account where name = ?";
		List<Account> accounts = super.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<Account>(Account.class), accountName);
		if (accounts.isEmpty()) {
			return null;
		}
		if (accounts.size() > 1) {
			throw new RuntimeException("结果集不唯一");
		}
		return accounts.get(0);
	}

	public void updateAccount(Account account) {
		String sql = "update account set name = ?, money = ? where id = ?";
		super.getJdbcTemplate().update(sql, account.getName(), account.getMoney(), account.getId());
	}
}
