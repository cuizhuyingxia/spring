package com.itheima.service;

/**
 * @Author Lian Flower
 * @Date 2019/9/14 16:04
 * @Version 1.0
 */

import com.itheima.domain.Account;

import java.util.List;

/**
 * 账户的业务层接口
 */
public interface IAccountService {

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

	/**
	 * 转账操作
	 * @param sourceName	转出账户名称
	 * @param targetName	转入账户名称
	 * @param money			转账金额
	 */
	void transfer(String sourceName, String targetName, Float money);
}
