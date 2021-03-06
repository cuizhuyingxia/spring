package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import org.springframework.stereotype.Repository;

/**
 * @Author Lian Flower
 * @Date 2019/9/12 12:54
 * @Version 1.0
 */

/**
 * 账户的持久层实现类
 */
@Repository("accountDao2")
public class AccountDaoImpl2 implements IAccountDao {

	/**
	 * 模拟保存账户
	 */
	public void saveAccount() {
		System.out.println("保存了");
	}
}
