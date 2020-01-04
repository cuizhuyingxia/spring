package com.itheima.factory;

/**
 * @Author Lian Flower
 * @Date 2019/9/15 17:23
 * @Version 1.0
 */

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建Service的代理对象的工厂
 */
public class BeanFactory {

	private IAccountService accountService;
	private TransactionManager transactionManager;

	public final void setTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * 获取Service的代理对象
	 * @return
	 */
	public IAccountService getAccountService() {
		return (IAccountService)Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
				accountService.getClass().getInterfaces(),
				new InvocationHandler() {
					/**
					 * 添加事务的支持
					 * @param proxy
					 * @param method
					 * @param args
					 * @return
					 * @throws Throwable
					 */
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Object rtValue = null;
						try {
							// 1. 开启事务
							transactionManager.beginTransaction();
							// 2. 执行操作
							rtValue = method.invoke(accountService, args);
							// 3. 提交事务
							transactionManager.commitTransaction();
							// 4. 返回结果
							return rtValue;
						} catch (Exception e) {
							// 5. 回滚事务
							transactionManager.rollbackTransaction();
					throw new RuntimeException(e);
				} finally {
					// 6. 释放连接
					transactionManager.release();
				}
			}
		});
	}
}
