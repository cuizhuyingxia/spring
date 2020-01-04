package com.itheima.utils;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * C3P0
 *
 */

/**
 * 获取C3P0连接池的工具类
 */
public class C3P0Util {

	// 创建数据库连接池对象
	private static ComboPooledDataSource ds = new ComboPooledDataSource();

	// 获取连接池
	public static DataSource getDataSource(){
		return ds;
	}
	
	// 获取连接
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}

	// 测试是否能正常获取连接池
	public static void main(String[] args) {
		System.out.println(getDataSource());
	}
}
