package com.itheima.service.impl;

/**
 * @Author Lian Flower
 * @Date 2019/9/15 19:16
 * @Version 1.0
 */

import com.itheima.service.IAccountService;
import org.springframework.stereotype.Service;

/**
 * 账户的业务层实现类
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {

	public void saveAccount() {
		System.out.println("执行了保存");
		//int i = 1 / 0;
	}

	public void updateAccount(int i) {
		System.out.println("执行了更新" + i);
	}

	public int deleteAccount() {
		System.out.println("执行了删除");
		return 0;
	}
}
