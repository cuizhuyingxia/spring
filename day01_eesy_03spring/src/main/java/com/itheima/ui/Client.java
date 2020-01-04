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
	 *
	 * 		ApplicationContext的三个常用实现类：
	 *			ClassPathXmlApplicationContext：			可以加载类路径下的配置文件，要求配置文件必须在类路径下，如果不在，则无法加载
	 *			FileSystemXmlApplicationContext			可以加载磁盘任意路径下的配置文件（前提是对磁盘有访问权限）
	 *			AnnotationConfigApplicationContext		用于读取注解配置
	 *
	 * 		核心容器的两个接口引发出的问题：
	 * 			ApplicationContext：			适用于单例对象
	 * 				它在构建核心容器的时，创建对象采用的策略是立即加载的方式，也就是说，只要一读取完配置文件，马上就创建配置文件中配置的对象
	 *			BeanFactory:				适用于多例对象
	 *				它在构建核心容器的时，创建对象采用的策略是延迟加载的方式，也就是说，只有当调用getBean方法时，才会去创建对象
	 *
	 * 			不过ApplicationContext是一个很智能的接口，它可以根据要创建的对象是单例还是多例，来决定使用立即加载还是延迟加载
	 * 					如果在配置文件中将某个对象设置为单例的（默认设置），那么	ApplicationContext接口会对该对象使用立即加载，即创建容器时就创建该对象
	 * 					如果在配置文件中将某个对象设置为多例的，那么ApplicationContext接口会对该对象使用延迟加载，即创建容器时不会创建该对象，而是用到该对象的时候，才会去创建该对象
	 * 																																使用getBean方法获取该对象时，才会创建该对象
	 * @param args
	 */
	public static void main(String[] args) {
		// 使用ApplicationContext获取核心容器对象
		// 1. 读取配置文件，获取核心容器对象（并创建配置文件中配置的对象）
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
		// ApplicationContext applicationContext = new FileSystemXmlApplicationContext("C:\\Users\\song\\Desktop\\bean.xml");
		// 2. 根据id获取对象
		IAccountService accountService = (IAccountService)applicationContext.getBean("accountService");
		// 也可以再传入一个字节码文件，这样spring就能自动进行类型转换，不需要我们自己再强转了
		IAccountDao accountDao = applicationContext.getBean("accountDao", IAccountDao.class);

		System.out.println(accountService);
		System.out.println(accountDao);

		accountService.saveAccount();

		/*// 使用BeanFactory获取核心容器对象
		// 1. 获取配置文件的类路径
		Resource resource = new ClassPathResource("bean.xml");
		// 2. 读取配置文件，获取核心容器对象（此时还没有创建配置文件中配置的对象，只有当调用getBean方法时，才会去创建配置文件中配置的对象）
		BeanFactory factory = new XmlBeanFactory(resource);
		// 3. 根据id获取对象
		IAccountService accountService = (IAccountService)factory.getBean("accountService");
		System.out.println(accountService);*/
	}
}
