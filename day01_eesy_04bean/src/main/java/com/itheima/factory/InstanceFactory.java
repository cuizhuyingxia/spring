package com.itheima.factory;

/**
 * @Author Lian Flower
 * @Date 2019/9/12 20:33
 * @Version 1.0
 */

import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;

/**
 * 模拟一个工厂类（该类可能存在于jar包中）
 */
public class InstanceFactory {

	/**
	 * 该方法可以返回一个IAccountService对象
	 * @return
	 */
	public IAccountService getAccountService() {
		return new AccountServiceImpl();
	}
}
