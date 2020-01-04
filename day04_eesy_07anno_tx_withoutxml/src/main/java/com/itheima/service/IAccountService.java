package com.itheima.service;

/**
 * @Author Lian Flower
 * @Date 2019/9/16 17:46
 * @Version 1.0
 */

import com.itheima.domain.Account;

/**
 * 账户的业务层接口
 */
public interface IAccountService {

	/**
	 * 根据id查询账户
	 * @param accountId
	 * @return
	 */
	Account findAccountById(Integer accountId);

	/**
	 * 转账
	 * @param sourceName	转出账户名称
	 * @param targetName	转入账户名称
	 * @param money			转账金额
	 */
	void transfer(String sourceName, String targetName, Float money);
}
