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
 */
public class JdbcDaoSupport {

	private JdbcTemplate jdbcTemplate;

	/*public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
*/
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		// 如果jdbcTemplate为空，就创建一个jdbcTemplate
		if (jdbcTemplate == null) {
			jdbcTemplate = createJdbcTemplate(dataSource);
		}
	}

	private JdbcTemplate createJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
