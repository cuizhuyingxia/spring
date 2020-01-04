package com.itheima.cglib;

/**
 * @Author Lian Flower
 * @Date 2019/9/15 15:48
 * @Version 1.0
 */

import com.itheima.proxy.IProducer;

/**
 * 模拟电脑生产厂家
 */
public class Producer {

	/**
	 * 销售电脑
	 * @param money
	 */
	public void saleProduct(float money) {
		System.out.println("销售出一台电脑，并拿到钱：" + money);
	}

	/**
	 * 售后服务
	 * @param money
	 */
	public void afterService(float money) {
		System.out.println("提供售后服务，服务费为：" + money);
	}
}
