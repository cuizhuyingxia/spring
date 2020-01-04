package com.itheima.proxy;

/**
 * @Author Lian Flower
 * @Date 2019/9/15 15:55
 * @Version 1.0
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 模拟一个消费者
 */
public class Client {

	public static void main(String[] args) {
		// 当匿名内部类访问外部成员变量时，要求外部成员变量是最终的，也就是说需要用final修饰
		final Producer producer = new Producer();


		/**
		 * 动态代理
		 * 		特点：代理对象的字节码随用随创建，随用随加载
		 * 		作用：不修改源码的基础上，对方法进行增强
		 * 		分类：基于接口的动态代理和基于子类的动态代理
		 *
		 * 			基于接口的动态代理：
		 * 				涉及的类：Proxy
		 * 				提供者：JDK官方
		 * 			如何创建代理对象：
		 * 				使用Proxy类中的newProxyInstance方法
		 * 			创建代理对象的要求：
		 * 				被代理的类至少实现一个接口，否则不能被代理
		 * 			newProxyInstance的参数：
		 *				ClassLoader：类加载器
		 *					用于加载代理对象的字节码，和被代理对象使用相同的类加载器
		 *				Class[]：接口类型的字节码数组
		 *					用于让代理对象和被代理对象有相同的方法，即让代理对象实现被代理对象的接口
		 *				InvocationHandler：	用于提供增强的代码
		 *					一般是写该接口的实现类，通常情况下使用匿名内部类，但不是必须的，
		 *						此接口的实现类，都是谁用谁写
		 *
		 */

		IProducer proxyProducer = (IProducer)Proxy.newProxyInstance(producer.getClass().getClassLoader(),
				producer.getClass().getInterfaces(),
				new InvocationHandler() {
					/**
					 * 使用代理对象执行被代理对象的任何方法，都会经过invoke()方法
					 * @param proxy		代理对象的引用
					 * @param method	执行的被代理对象的方法
					 * @param args		执行的被代理对象的方法，所需要的参数
					 * @return
					 * @throws Throwable
					 */
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// 提供增强的代码
						// 1. 获取参数
						Float money = (Float)args[0];
						// 2. 判断方法名是不是saleProduct
						if ("saleProduct".equals(method.getName())) {
							// 对参数进行修改
							method.invoke(producer, money * 0.8f);
						}
						return null;
					}
				});

		proxyProducer.saleProduct(1000f);	// 销售出一台电脑，并拿到钱：1000.0
	}
}
