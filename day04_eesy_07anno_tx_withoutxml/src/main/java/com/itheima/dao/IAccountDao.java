package com.itheima.dao;

/**
 * @Author Lian Flower
 * @Date 2019/9/16 15:25
 * @Version 1.0
 */

import com.itheima.domain.Account;

/**
 * 账户的持久层接口
 */
public interface IAccountDao {

	/**
	 * 根据id查询账户
	 * @param accountId
	 * @return
	 */
	Account findAccountById(Integer accountId);

	/**
	 * 根据名称查询账户
	 * @param accountName
	 * @return
	 */
	Account findAccountByName(String accountName);

	/**
	 * 更新账户
	 * @param account
	 */
	void updateAccount(Account account);
}
