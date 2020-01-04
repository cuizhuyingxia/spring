package com.itheima.utils;

/**
 * @Author Lian Flower
 * @Date 2019/9/15 13:45
 * @Version 1.0
 */

import org.aspectj.lang.ProceedingJoinPoint;

import java.sql.Connection;

/**
 * 和事务关系相关的工具类，包含：开启事务、提交事务、回滚事务和释放连接
 */
public class TransactionManager {

	private ConnectionUtils connectionUtils;

	public void setConnectionUtils(ConnectionUtils connectionUtils) {
		this.connectionUtils = connectionUtils;
	}

	/**
	 * 开启事务
	 */
	public void beginTransaction() {
		try {
			System.out.println("前置通知，开启事务");
			// 获取当前线程上的连接
			Connection connection = connectionUtils.getThreadConnection();
			// 开启事务（关闭自动提交）
			connection.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 提交事务
	 */
	public void commitTransaction() {
		try {
			System.out.println("后置通知，提交事务");
			// 获取当前线程上的连接
			Connection connection = connectionUtils.getThreadConnection();
			// 提交事务
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 回滚事务
	 */
	public void rollbackTransaction() {
		try {
			System.out.println("异常通知，回滚事务");
			// 获取当前线程上的连接
			Connection connection = connectionUtils.getThreadConnection();
			// 回滚事务
			connection.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 释放连接
	 */
	public void release() {
		try {
			System.out.println("最终通知，释放连接");
			// 获取当前线程上的连接
			Connection connection = connectionUtils.getThreadConnection();
			// 释放连接
			connection.close();	// 归还到连接池中
			// 把连接和线程进行解绑
			connectionUtils.removeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 环绕通知:
	 * 		环绕通知是spring框架为我们提供的一种可以在代码中手动控制通知方法（增强方法）何时执行的方式
	 * 			我们可以在环绕通知中，通过调用spring提供的ProceedingJoinPoint接口的proceed()方法，来执行业务层的切入点方法
	 * 				可以在执行切入点方法之前，执行一些其它方法或操作，即前置通知
	 * 					可以在切入点方法正常执行之后，执行一些其它方法或操作，即后置通知
	 * 						可以在catch中执行一些其它方法或操作，即异常通知
	 * 							可以在finally中执行一些其它方法或操作，即最终通知
	 */
	public Object aroundTransaction(ProceedingJoinPoint proceedingJoinPoint) {
		Object returnValue = null;
		try {
			// 获取proceed方法所需要的参数
			Object[] args = proceedingJoinPoint.getArgs();
			// 前置通知
			beginTransaction();
			// 调用proceed方法，从而执行业务层的切入点方法
			proceedingJoinPoint.proceed(args);
			// 后置通知
			commitTransaction();
			return returnValue;
		} catch (Throwable t) {
			// 异常通知
			rollbackTransaction();
			throw new RuntimeException(t);
		} finally {
			// 最终通知
			release();
		}

	}
}
