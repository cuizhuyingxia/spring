package com.itheima.dbassit;

/**
 * @Author Lian Flower
 * @Date 2019/9/14 11:03
 * @Version 1.0
 */

import com.itheima.handler.ResultSetHandler;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * 执行sql语句的工具类
 */
public class DBAssit {

	private DataSource dataSource;

	/**
	 * 构造方法，用于获取连接池
	 * @param dataSource
	 */
	public DBAssit(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 封装执行增删改的方法
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			// 1. 获取数据库连接
			conn = dataSource.getConnection();
			// 2. 创建预处理对象
			pstm = conn.prepareStatement(sql);
			// 3. 取出sql语句中参数的元信息（有几个参数，都是什么类型）
			ParameterMetaData metaData = pstm.getParameterMetaData();
			int parameterCount = metaData.getParameterCount();
			// 4. 判断调用方法时提供的参数和sql语句中的参数个数是否一致
			if (parameterCount != 0) {
				if (params == null) {
					throw new NullPointerException("没有提供sql语句需要的参数");
				}
				if (params.length != parameterCount) {
					throw new RuntimeException("传入的参数个数和sql语句所需要的个数不一致");
				}
			}
			// 5. 给sql语句中的参数赋值
			for (int i = 0; i < parameterCount; i++) {
				// 因为预处理对象中，给参数赋值时，参数的索引是从1开始的，所以需要将i+1
				pstm.setObject(i+1, params[i]);
			}
			// 6. 执行sql语句
			int res = pstm.executeUpdate();
			// 7. 返回执行结果
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			release(conn, pstm, null);
		}
	}

	// 查询一个								// ResultSetHandler为封装结果集的接口
	public <T> T queryForObject(String sql, ResultSetHandler<T> rsh, Object... params) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// 1. 获取数据库连接
			conn = dataSource.getConnection();
			// 2. 创建预处理对象
			pstm = conn.prepareStatement(sql);
			// 3. 取出sql语句中参数的元信息（有几个参数，都是什么类型）
			ParameterMetaData metaData = pstm.getParameterMetaData();
			int parameterCount = metaData.getParameterCount();
			// 4. 判断调用方法时提供的参数和sql语句中的参数个数是否一致
			if (parameterCount > 0) {
				if (params == null) {
					throw new NullPointerException("没有提供sql语句需要的参数");
				}
				if (params.length != parameterCount) {
					throw new RuntimeException("传入的参数个数和sql语句所需要的个数不一致");
				}
			}
			// 5. 给sql语句中的参数赋值
			for (int i = 0; i < parameterCount; i++) {
				// 因为预处理对象中，给参数赋值时，参数的索引是从1开始的，所以需要将i+1
				pstm.setObject(i+1, params[i]);
			}
			// 6. 执行sql语句
			rs = pstm.executeQuery();
			// 7. 返回执行结果
			return rsh.handle(rs);	// 对结果集进行封装
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			release(conn, pstm, rs);
		}
	}

	// 查询所有						// ResultSetHandler为封装结果集的接口
	public <T> List<T> query(String sql, ResultSetHandler<T> rsh, Object... params) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// 1. 获取数据库连接
			conn = dataSource.getConnection();
			// 2. 创建预处理对象
			pstm = conn.prepareStatement(sql);
			// 3. 取出sql语句中参数的元信息（有几个参数，都是什么类型）
			ParameterMetaData metaData = pstm.getParameterMetaData();
			int parameterCount = metaData.getParameterCount();
			// 4. 判断调用方法时提供的参数和sql语句中的参数个数是否一致
			if (parameterCount > 0) {
				if (params == null) {
					throw new NullPointerException("没有提供sql语句需要的参数");
				}
				if (params.length != parameterCount) {
					throw new RuntimeException("传入的参数个数和sql语句所需要的个数不一致");
				}
			}
			// 5. 给sql语句中的参数赋值
			for (int i = 0; i < parameterCount; i++) {
				// 因为预处理对象中，给参数赋值时，参数的索引是从1开始的，所以需要将i+1
				pstm.setObject(i+1, params[i]);
			}
			// 6. 执行sql语句
			rs = pstm.executeQuery();
			// 7. 返回执行结果
			return rsh.handleAll(rs);	// 对结果集进行封装
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			release(conn, pstm, rs);
		}
	}

	public void release(Connection conn, PreparedStatement pstm, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
