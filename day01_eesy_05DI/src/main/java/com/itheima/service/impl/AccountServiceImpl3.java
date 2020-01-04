package com.itheima.service.impl;

import com.itheima.service.IAccountService;

import java.util.*;

/**
 * @Author Lian Flower
 * @Date 2019/9/12 12:50
 * @Version 1.0
 */

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl3 implements IAccountService {

	private String[] myStrs;
	private List<String> myList;
	private Set<String> mySet;
	private Map<String,String> myMap;
	private Properties myProps;

	public void setMyStrs(String[] myStrs) {
		this.myStrs = myStrs;
	}

	public void setMyList(List<String> myList) {
		this.myList = myList;
	}

	public void setMySet(Set<String> mySet) {
		this.mySet = mySet;
	}

	public void setMyMap(Map<String, String> myMap) {
		this.myMap = myMap;
	}

	public void setMyProps(Properties myProps) {
		this.myProps = myProps;
	}

	/**
	 * 模拟保存账户
	 */
	public void saveAccount() {
		// System.out.println(myStrs);
		// 对于数组类型的数据，需要使用toString转换为字符串，不然打印的结果，不是数组中的内容，而是在内存中的地址
		System.out.println(Arrays.toString(myStrs));
		System.out.println(myList);
		System.out.println(myMap);
		System.out.println(myProps);
		System.out.println(mySet);
	}
}
