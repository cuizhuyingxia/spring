package com.itheima.factory;

/**
 * @Author Lian Flower
 * @Date 2019/9/12 13:11
 * @Version 1.0
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 一个创建Bean对象的工厂
 *
 * 		Bean：在计算机英语中，有可重用组件的含义
 * 		JavaBean：指使用java语言编写的可重用组件
 * 			JavaBean不等于实体类，JavaBean的范围远大于实体类
 * 			实体类只是可重用组件的一种，而JavaBean指的是所有使用java语言编写的可重用组件，如service层、dao层等等
 *
 *		我们可以创建一个Bean，用于获取service实现类对象或dao实现类对象
 *			1. 在配置文件中配置service和dao
 *				配置内容：唯一标识=全限定类名（key=value）
 *			2. 通过读取配置文件中的配置内容，反射创建对象
 */
public class BeanFactory {
	private static Properties properties;
	// 创建一个map，用于存放我们要创建的对象，我们把它称之为容器
	// 以此来实现单例的效果，即当BeanFactory类加载时，读取配置文件，使用反射创建所有的对象，将对象存入到map中
	// 然后每次获取对象时，都是从map中获取，而不用每次都使用newInstance获取对象
	private static Map<String,Object> beans;

	static {
		try {
			// 读取配置文件
			properties = new Properties();
			InputStream inputStream = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
			properties.load(inputStream);
			// 实例化map容器
			beans = new HashMap<String,Object>();
			// 获取配置文件中所有的key
			Enumeration<Object> keys = properties.keys();
			// 遍历所有的key
			while (keys.hasMoreElements()) {
				// 取出每一个key
				String key = keys.nextElement().toString();
				// 根据key获取value，也就是全类名
				String fullClassName = properties.getProperty(key);
				// 使用反射创建对象
				Object value = Class.forName(fullClassName).newInstance();
				// 将创建的对象存储到map容器中
				beans.put(key, value);
			}
		} catch (Exception e) {
			throw new ExceptionInInitializerError("初始化异常");
		}
	}


	/**
	 * 获取service实现类对象或dao实现类对象
	 * @param beanName	实现类的名称，如accountService	accountDao
	 * @return
	 */
	public static Object getBean(String beanName) {
		// 从map容器中获取对象
		return beans.get(beanName);
	}



	/**
	 * 获取service实现类对象或dao实现类对象
	 * @param beanName	实现类的名称，如accountService	accountDao
	 * @return
	 */
	/*public static Object getBean(String beanName) {
		Object Bean = null;
		// 根据传入的实现类的名称，从配置文件中获取该类的全类名
		String fullClassName = properties.getProperty(beanName);
		// 使用反射加载class文件，创建对象
		try {
			Bean = Class.forName(fullClassName).newInstance();	// newInstance每次都会调用默认的构造函数创建对象
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Bean;
	}*/

}
