package com.itheima.handler.impl;

import com.itheima.handler.ResultSetHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/14 13:41
 * @Version 1.0
 */

/**
 * 封装结果集接口的实现类
 * 		此类实现的是，将结果集封装到一个指定的实体类对象中
 * 		使用要求：实体类中的属性名必须和sql语句查出来的列名一致
 * @param <T>
 */
public class BeanHandler<T> implements ResultSetHandler<T> {

	// 指定实体类的Class对象（用于将结果集封装到Class对象对应的实体类中）
	private Class<T> domainClass;

	public BeanHandler(Class<T> domainClass) {
		this.domainClass = domainClass;
	}

	/**
	 * 将结果集封装到domainClass对应的实体类中（封装单个对象）
	 * @param rs
	 * @return
	 */
	public T handle(ResultSet rs) {
		try {
			// 1. 创建实体类对象
			T bean = domainClass.newInstance();
			// 2. 判断是否有结果集
			if (rs.next()) {
				// 3. 得到结果集中的所有列名
				// 先获取结果集的元信息
				ResultSetMetaData metaData = rs.getMetaData();
				// 获取结果集的列数
				int columnCount = metaData.getColumnCount();
				// 遍历列数
				for (int i = 0; i < columnCount; i++) {
					// 取出列名
					// 因为元信息中的索引是从1，开始的，所以需要i + 1
					String columnName = metaData.getColumnName(i + 1);
					// 因为列名对应的就是实体类中的属性，所以可以根据列名和Class对象获取属性的描述器
					// 描述器中有：该属性以及该属性的get和set方法（注意属性必须是私有的）
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, domainClass);
					// 获取属性的写入方法
					Method writeMethod = propertyDescriptor.getWriteMethod();
					// 4. 获取列名对应的值
					Object columnValue = rs.getObject(columnName);
					// 5. 将列名对应的值赋给属性
					writeMethod.invoke(bean, columnValue);
				}
			}
			// 返回结果
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将结果集封装到domainClass对应的实体类中（封装所有）
	 * @param rs
	 * @return
	 */
	public List<T> handleAll(ResultSet rs) {
		List<T> list = new ArrayList<T>();
		try {
			// 1. 遍历结果集
			while (rs.next()) {
				// 2. 创建实体类对象
				T bean = domainClass.newInstance();
				// 3. 得到结果集中的所有列名
				// 先获取结果集的元信息
				ResultSetMetaData metaData = rs.getMetaData();
				// 获取结果集的列数
				int columnCount = metaData.getColumnCount();
				// 遍历列数
				for (int i = 0; i < columnCount; i++) {
					// 取出列名
					// 因为元信息中的索引是从1，开始的，所以需要i + 1
					String columnName = metaData.getColumnName(i + 1);
					// 因为列名对应的就是实体类中的属性，所以可以根据列名和Class对象获取属性的描述器
					// 描述器中有：该属性以及该属性的get和set方法（注意属性必须是私有的）
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, domainClass);
					// 获取属性的写入方法
					Method writeMethod = propertyDescriptor.getWriteMethod();
					// 4. 获取列名对应的值
					Object columnValue = rs.getObject(columnName);
					// 5. 将列名对应的值赋给属性
					writeMethod.invoke(bean, columnValue);
				}
				list.add(bean);
			}
			// 返回结果
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
