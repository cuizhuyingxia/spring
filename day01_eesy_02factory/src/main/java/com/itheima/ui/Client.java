package com.itheima.ui;

/**
 * @Author Lian Flower
 * @Date 2019/9/12 13:08
 * @Version 1.0
 */

import com.itheima.factory.BeanFactory;
import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {
	public static void main(String[] args) {
		//IAccountService accountService = new AccountServiceImpl();
		for (int i = 0; i < 5; i++) {
			IAccountService accountService = (IAccountService)BeanFactory.getBean("accountService");
			System.out.println(accountService);
			accountService.saveAccount();
		}

	}
}
