package com.itheima.test;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.domain.Account;
import org.junit.Test;

import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/14 13:15
 * @Version 1.0
 */
public class AccountDapImplTest {

	/**
	 * 保存账户
	 */
	@Test
	public void testSaveAccount() {
		Account account = new Account();
		account.setName("coco");
		account.setMoney(1000f);

		IAccountDao accountDao = new AccountDaoImpl();
		accountDao.saveAccount(account);
	}

	/**
	 * 根据id查询账户
	 */
	@Test
	public void testFindAccountById() {
		IAccountDao accountDao = new AccountDaoImpl();
		Account account = accountDao.findAccountById(1);
		System.out.println(account);
	}

	/**
	 * 查询所有账户
	 */
	@Test
	public void testFindAll() {
		IAccountDao accountDao = new AccountDaoImpl();
		List<Account> accounts = accountDao.findAll();
		for (Account account : accounts) {
			System.out.println(account);
		}
	}
}
