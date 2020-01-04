package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/14 16:09
 * @Version 1.0
 */

/**
 * 账户的业务层接口的实现类
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {

	// 使用注解注入IAccountDao对象（不需要set方法）
	// @Autowired：自动按照数据类型注入，只要容器中有唯一的一个bean对象的类型和要注入的类型匹配，就可以注入成功
	@Autowired
	private IAccountDao accountDao;

	public List<Account> findAllAccount() {
		return accountDao.findAllAccount();
	}

	public Account findAccountById(Integer accountId) {
		return accountDao.findAccountById(accountId);
	}

	public void updateAccount(Account account) {
		accountDao.updateAccount(account);
	}

	public void saveAccount(Account account) {
		accountDao.saveAccount(account);
	}

	public void deleteAccount(Integer accountId) {
		accountDao.deleteAccount(accountId);
	}
}
