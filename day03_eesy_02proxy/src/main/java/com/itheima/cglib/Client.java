package com.itheima.cglib;

/**
 * @Author Lian Flower
 * @Date 2019/9/15 15:55
 * @Version 1.0
 */

import com.itheima.proxy.IProducer;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

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
		 * 			基于子类的动态代理：
		 * 				涉及的类：Enhancer
		 * 				提供者：第三方cglib库
		 * 			如何创建代理对象：
		 * 				使用Enhancer类中的create方法
		 * 			创建代理对象的要求：
		 * 				被代理类不能是最终类（即不能是final修饰的类）
		 * 			create的参数：
		 *				Class：字节码
		 *					用于指定被代理对象的字节码
		 *				Callback：用于提供增强的代码
		 *					一般是写该接口的子接口实现类，子接口为：MethodInterceptor
		 *
		 */
		Producer cglibProducer = (Producer)Enhancer.create(producer.getClass(), new MethodInterceptor() {
			/**
			 * 使用代理对象执行被代理对象的任何方法，都会经过intercept()方法
			 * @param o				代理对象的引用
			 * @param method		执行的被代理对象的方法
			 * @param objects		执行的被代理对象的方法，所需要的参数
			 * @param methodProxy	执行的被代理对象的方法，这个方法的代理对象
			 * @return
			 * @throws Throwable
			 */
			public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
				// 提供增强的代码
				// 1. 获取参数
				Float money = (Float)objects[0];
				// 2. 判断方法名是不是saleProduct
				if ("saleProduct".equals(method.getName())) {
					// 对参数进行修改
					method.invoke(producer, money * 0.8f);
				}
				return null;
			}
		});
		cglibProducer.saleProduct(1000f);
	}
}
