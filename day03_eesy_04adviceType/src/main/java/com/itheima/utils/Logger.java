package com.itheima.utils;

/**
 * @Author Lian Flower
 * @Date 2019/9/15 19:18
 * @Version 1.0
 */

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 用于记录日志的工具类，它里面提供了公共的代码
 */
public class Logger {

	/**
	 * 前置通知
	 */
	public void beforePrintLog() {
		System.out.println("前置通知Logger类中的printLog方法开始记录日志了。。。");
	}

	/**
	 * 后置通知
	 */
	public void afterReturningPrintLog() {
		System.out.println("后置通知Logger类中的printLog方法开始记录日志了。。。");
	}

	/**
	 * 异常通知
	 */
	public void afterThrowingPrintLog() {
		System.out.println("异常通知Logger类中的printLog方法开始记录日志了。。。");
	}

	/**
	 * 最终通知
	 */
	public void afterPrintLog() {
		System.out.println("最终通知Logger类中的printLog方法开始记录日志了。。。");
	}

	/**
	 * 环绕通知:
	 * 		环绕通知是spring框架为我们提供的一种可以在代码中手动控制通知方法（增强方法）何时执行的方式
	 * 			我们可以在环绕通知中，通过调用spring提供的ProceedingJoinPoint接口的proceed()方法，来执行业务层的切入点方法
	 * 				可以在执行切入点方法之前，执行一些其它方法或操作，即前置通知
	 * 					可以在切入点方法正常执行之后，执行一些其它方法或操作，即后置通知
	 * 						可以在catch中执行一些其它方法或操作，即异常通知
	 * 							可以在finally中执行一些其它方法或操作，即最终通知
	 */
	public Object aroundPrintLog(ProceedingJoinPoint proceedingJoinPoint) {
		Object returnValue = null;
		try {
			// 获取proceed方法所需要的参数
			Object[] args = proceedingJoinPoint.getArgs();
			System.out.println("前置通知");
			// 调用proceed方法，从而执行业务层的切入点方法
			proceedingJoinPoint.proceed(args);
			System.out.println("后置通知");
			return returnValue;
		} catch (Throwable t) {
			System.out.println("异常通知");
			throw new RuntimeException(t);
		} finally {
			System.out.println("最终通知");
		}

	}
}

