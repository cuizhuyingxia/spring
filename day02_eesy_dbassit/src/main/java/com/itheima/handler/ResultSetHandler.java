package com.itheima.handler;

import java.sql.ResultSet;
import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/14 13:34
 * @Version 1.0
 */

/**
 * 封装结果集的接口
 * @param <T>
 */
public interface ResultSetHandler<T> {

	/**
	 * 封装结果集的方法（封装单个对象）
	 * @param rs
	 * @return
	 */
	T handle(ResultSet rs);

	/**
	 * 封装结果集的方法（封装所有）
	 * @param rs
	 * @return
	 */
	List<T> handleAll(ResultSet rs);
}
