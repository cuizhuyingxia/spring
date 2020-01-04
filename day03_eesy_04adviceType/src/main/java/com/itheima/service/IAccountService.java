package com.itheima.service;

/**
 * @Author Lian Flower
 * @Date 2019/9/15 19:14
 * @Version 1.0
 */

/**
 * 账户的业务层接口
 */
public interface IAccountService {

	/**
	 * 模拟保存账户
	 */
	void saveAccount();

	/**
	 * 模拟更新账户
	 * @param i
	 */
	void updateAccount(int i);

	/**
	 * 模拟删除账户
	 * @return
	 */
	int deleteAccount();
}
