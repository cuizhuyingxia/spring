package com.itheima.dao;

import com.itheima.domain.Account;

import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/14 10:55
 * @Version 1.0
 */

/**
 * 账户的持久层接口
 */
public interface IAccountDao {

	/**
	 * 保存账户
	 * @param account
	 */
	void saveAccount(Account account);

	/**
	 * 删除账户
	 * @param accountId
	 */
	void deleteAccount(Integer accountId);

	/**
	 * 更新账户
	 * @param account
	 */
	void updateAccount(Account account);

	/**
	 * 根据id查询账户
	 * @param accountId
	 * @return
	 */
	Account findAccountById(Integer accountId);

	/**
	 * 查询所有账户
	 * @return
	 */
	List<Account> findAll();
}
