package com.itheima.ui;

/**
 * @Author Lian Flower
 * @Date 2019/9/12 13:08
 * @Version 1.0
 */

import com.itheima.dao.IAccountDao;
import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {

	/**
	 * 获取spring的Ioc核心容器，并根据id获取对象
	 * @param args
	 */
	public static void main(String[] args) {
		// 使用ApplicationContext获取核心容器对象
		// 1. 读取配置文件，获取核心容器对象（并创建配置文件中配置的对象）
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
		// 2. 根据id获取对象
		IAccountService accountService = (IAccountService)applicationContext.getBean("accountService");
		IAccountService accountService1 = (IAccountService)applicationContext.getBean("accountService");
		System.out.println(accountService == accountService1);

		accountService.saveAccount();
		applicationContext.close();


	}
}
