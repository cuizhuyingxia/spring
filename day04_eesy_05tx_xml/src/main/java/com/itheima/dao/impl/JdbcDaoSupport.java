package com.itheima.dao.impl;

/**
 * @Author Lian Flower
 * @Date 2019/9/16 15:43
 * @Version 1.0
 */

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 此类用于抽取dao中的重复代码
 * 		因为AccountDaoImpl继承了该类，所以给AccountDaoImpl注入的数据源，也会注入到该类中
 *
 * 		其实spring也为我们提供了一个名为JdbcDaoSupport的类，功能也是用于抽取dao中的重复代码
 * 			和我们这个类中的功能是一样的
 */
public class JdbcDaoSupport {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	// 注入数据源
	public void setDataSource(DataSource dataSource) {
		// new一个JdbcTemplate
		if (jdbcTemplate == null) {
			jdbcTemplate = createJdbcTemplate(dataSource);
		}
	}

	private JdbcTemplate createJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
