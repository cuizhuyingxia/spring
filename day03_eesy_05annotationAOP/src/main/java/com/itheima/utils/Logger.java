package com.itheima.utils;

/**
 * @Author Lian Flower
 * @Date 2019/9/15 19:18
 * @Version 1.0
 */

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 用于记录日志的工具类，它里面提供了公共的代码
 */
@Component("logger")
@Aspect	// 标识当前类是一个切面
public class Logger {

	// 配置切入点表达式
	@Pointcut("execution(* com.itheima.service.impl.*.*(..))")
	private void pt1(){}
	/**
	 * 前置通知
	 */
	//@Before("pt1()")
	public void beforePrintLog() {
		System.out.println("前置通知Logger类中的printLog方法开始记录日志了。。。");
	}

	/**
	 * 后置通知
	 */
	//@AfterReturning("pt1()")
	public void afterReturningPrintLog() {
		System.out.println("后置通知Logger类中的printLog方法开始记录日志了。。。");
	}

	/**
	 * 异常通知
	 */
	//@AfterThrowing("pt1()")
	public void afterThrowingPrintLog() {
		System.out.println("异常通知Logger类中的printLog方法开始记录日志了。。。");
	}

	/**
	 * 最终通知
	 */
	//@After("pt1()")
	public void afterPrintLog() {
		System.out.println("最终通知Logger类中的printLog方法开始记录日志了。。。");
	}

	/**
	 * 注意：在使用注解配置AOP时，通知的执行顺序并不是：前置通知 后置通知/异常通知 最终通知
	 * 										  而是：前置通知 最终通知 后置通知/异常通知
	 *
	 *							为什么会出现这种情况呢？这个属于是spring框架本身的问题，为什么这样设计，暂时还不知道
	 *								不过在使用注解配置AOP时，我们可以使用环绕通知，
	 *									环绕通知的执行顺序是正常的：前置通知 后置通知/异常通知 最终通知
	 */

	/**
	 * 环绕通知:
	 * 		环绕通知是spring框架为我们提供的一种可以在代码中手动控制通知方法（增强方法）何时执行的方式
	 * 			我们可以在环绕通知中，通过调用spring提供的ProceedingJoinPoint接口的proceed()方法，来执行业务层的切入点方法
	 * 				可以在执行切入点方法之前，执行一些其它方法或操作，即前置通知
	 * 					可以在切入点方法正常执行之后，执行一些其它方法或操作，即后置通知
	 * 						可以在catch中执行一些其它方法或操作，即异常通知
	 * 							可以在finally中执行一些其它方法或操作，即最终通知
	 */
	@Around("pt1()")
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

