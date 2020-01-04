package com.itheima.service.impl;

import com.itheima.service.IAccountService;

/**
 * @Author Lian Flower
 * @Date 2019/9/12 12:50
 * @Version 1.0
 */

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

	public AccountServiceImpl() {
		System.out.println("对象被创建了");
	}

	public void init() {
		System.out.println("对象初始化了");
	}

	public void destroy() {
		System.out.println("对象销毁了");
	}

	/**
	 * 模拟保存账户
	 */
	public void saveAccount() {
		System.out.println("service的saveAccount执行了");
	}
}
