package com.itheima.utils;

/**
 * @Author Lian Flower
 * @Date 2019/9/15 13:27
 * @Version 1.0
 */

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 获取连接的工具类，它用于从数据源中获取一个连接，并且实现和线程的绑定
 */
public class ConnectionUtils {

	// 创建ThreadLocal对象
	private ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 获取当前线程上的连接
	 * @return
	 */
	public Connection getThreadConnection() {
		try {
			// 1. 先从当前线程中获取连接
			Connection connection = threadLocal.get();
			// 2. 判断当前线程上是否有连接
			if (connection == null) {
				// 如果没有，说明是第一次获取连接
				// 那么就从数据源中获取一个连接，并且存入当前线程中
				connection = dataSource.getConnection();
				threadLocal.set(connection);
			}
			// 返回当前线程上的连接
			return connection;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 把连接和线程进行解绑
	 */
	public void removeConnection() {
		threadLocal.remove();
	}

	/*
	为什么要将连接和线程解绑呢？
		1. 我们的连接都是从连接池中获取的，连接池的好处就是把获取连接的操作放在应用一加载时就执行，
			在web工程中，当我们启动tomcat加载应用时，就会帮我们创建一些连接，从而在后续项目运行阶段，
				不再跟数据库获取连接，保证了使用连接时的执行效率

		2. 而我们使用的线程时，也是从服务器的线程池中获取的线程，也是当tomcat一启动时，就初始化一大堆的线程，
			放到一个容器中，接下来，每次使用线程时，都是从线程池中拿出来给我们使用

		3. 我们在调用连接的close方法时，并不是把连接关闭了，而是归还到了连接池中。
			同理，我们在调用线程的close方法时，也并不是把线程关闭了，而是将线程归还到了线程池中。
				只不过，虽然将线程归还到了线程池，但是线程上面还绑着一个已经被关闭的连接，
					当我们下次再获取到这个线程时，线程上面依然会绑着这个已经被关闭的且不能使用的连接，
						那么当我们在执行ConnectionUtils.getThreadConnection方法获取连接时，就会得到一个不能用的连接，
							这不是我们想要的结果
								所以我们应该在线程用完以后，把连接和线程进行解绑
	 */

}
