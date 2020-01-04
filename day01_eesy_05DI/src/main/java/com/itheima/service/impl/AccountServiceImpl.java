package com.itheima.service.impl;

import com.itheima.service.IAccountService;

import java.util.Date;

/**
 * @Author Lian Flower
 * @Date 2019/9/12 12:50
 * @Version 1.0
 */

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {
		// 如果是经常变化的数据，并不适用于注入的方式
		private String name;
		private Integer age;
		private Date birthday;

	public AccountServiceImpl(String name, Integer age, Date birthday) {
		this.name = name;
		this.age = age;
		this.birthday = birthday;
	}


	/**
	 * 模拟保存账户
	 */
	public void saveAccount() {
		System.out.println("service的saveAccount执行了   "+name+", "+age+", "+birthday);
	}
}
