package com.itheima.utils;

/**
 * @Author Lian Flower
 * @Date 2019/9/15 13:45
 * @Version 1.0
 */

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


}
