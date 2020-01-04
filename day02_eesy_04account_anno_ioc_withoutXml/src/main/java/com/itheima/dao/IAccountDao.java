package com.itheima.dao;

import com.itheima.domain.Account;

import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/14 16:10
 * @Version 1.0
 */

/**
 * 账户持久层接口
 */
public interface IAccountDao {

	/**
	 * 查询所有账户
	 * @return
	 */
	List<Account> findAllAccount();

	/**
	 * 根据id查询账户
	 * @param accountId
	 * @return
	 */
	Account findAccountById(Integer accountId);

	/**
	 * 更新账户
	 * @param account
	 */
	void updateAccount(Account account);

	/**
	 * 保存账户
	 * @param account
	 */
	void saveAccount(Account account);

	/**
	 * 根据id删除账户
	 * @param accountId
	 */
	void deleteAccount(Integer accountId);
}
