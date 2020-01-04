package com.itheima.ui;

/**
 * @Author Lian Flower
 * @Date 2019/9/12 13:08
 * @Version 1.0
 */

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
		// 1. 读取配置文件，获取核心容器对象（并创建配置文件中配置的对象）
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
		// 2. 根据id获取对象
		IAccountService accountService = (IAccountService)applicationContext.getBean("accountService");
		accountService.saveAccount();

		/*
		注意：main方法是一切应用程序的入口，当main方法结束之后，当前应用中线程占用的内存会被全部释放，
				核心容器占用的内容，自然也会被销毁
				所以如果是单例对象，当main方法结束后，核心容器还没来得及调用销毁方法，核心容器就已经被销毁了
		 */
		// 也可以手动销毁容器
		applicationContext.close();
	}
}
