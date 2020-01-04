package com.itheima.jdbc;

/**
 * @Author Lian Flower
 * @Date 2019/9/12 11:13
 * @Version 1.0
 */

import java.sql.*;

/**
 * 程序的耦合
 *
 * 		耦合：程序间的依赖关系
 * 				包括：
 * 					类之间的依赖
 * 					方法之间的依赖
 *
 * 		解耦：降低程序间的依赖
 *				在实际开发中，应该做到编译期不依赖，运行时才依赖，以此来实现程序间的解耦
 *
 *		解耦的思路：
 *			第一步：使用反射来创建对象，避免使用new关键字
 *			第二步：通过读取配置文件获取要创建对象的全限定类名
 */
public class JdbcDemo1 {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// 1. 注册驱动
		/*
		* 两种方式：
		* 			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		* 			直接把驱动new进来：这种方式是编译期依赖，假设没有Driver这个类，那么编译期程序就会报错
		*
		* 			Class.forName("com.mysql.jdbc.Driver");
		* 			使用反射加载驱动：这种方式是运行时依赖，即使没有Driver这个类，在编译期也不会报错，运行时才会报错
		* 							  因为反射机制，是在程序运行时才会去加载类，所以编译期不会报错，运行时才会报错
		* */
		Class.forName("com.mysql.jdbc.Driver");

		// 2. 获取连接
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eesy?characterEncoding=utf8&useSSL=false", "root", "root");
		// 3. 获取操作数据库的预处理对象
		PreparedStatement preparedStatement = connection.prepareStatement("select * from account");
		// 4. 执行SQL，得到结果集4
		ResultSet resultSet = preparedStatement.executeQuery();
		// 5. 遍历结果集
		while (resultSet.next()) {
			System.out.print(resultSet.getString("name") + " - ");
			System.out.println(resultSet.getString("money"));
		}
		// 6. 释放资源
		resultSet.close();
		preparedStatement.close();
		connection.close();
	}
}
