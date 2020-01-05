package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import config.JdbcConfig;
import config.SpringConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Author Lian Flower
 * @Date 2019/9/14 17:01
 * @Version 1.0
 */

/**
 * spring整合junit配置
 * 		前因：在执行测试方法时，每次都需要编写读取配置、创建容器的代码，这样就会造成代码的冗余
 * 		解决：
 * 			1. 编写一个init()方法，在init方法中编写读取配置、创建容器的代码，然后加上@Before注解，让测试方法执行前，先执行init方法
 * 			2. 因为单元测试是给测试工程师用的，测试工程师可能不懂spring容器，也不会写读取配置、创建容器的代码，那该怎么办呢？
 * 				我们可以对junit进行整合，即替换junit集成的main方法（有@Test注解的方法，最终在执行时，执行的都是junit集成的main方法），
 * 						1. 导入spring整合junit的jar包（坐标）
 * 							spring-test
 * 						2. 使用junit提供的注解，将junit集成的main方法替换为spring提供的SpringJUnit4ClassRunner，
 * 							它可以帮我们读取配置、创建容器。（原有的junit集成的main方法不会帮我们读取配置、创建容器）
 * 							@RunWith(SpringJUnit4ClassRunner.class)
 * 						3. 告知spring的运行器，spring的Ioc配置是基于xml的还是注解的，并说明位置
 * 							@ContextConfiguration
 * 								属性：
 * 									location：指定xml文件的位置，需要加上classpath关键字，表示文件在类路径下
 * 									classes：指定注解的配置类的位置，传入配置类的字节码即可
 * 						4. 使用注解注入所需要的对象，例如：
 * 								@Autowired
 *								private IAccountService accountService;
 *
 *						注意：当我们使用spring 5.x版本的时候，要求整合junit的jar包spring-test的版本必须是4.12及以上
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountServiceTest {

	@Autowired
	private IAccountService accountService;

	@Test
	public void testFindAll() {
		// 3. 执行方法
		List<Account> accounts = accountService.findAllAccount();
		for (Account account : accounts) {
			System.out.println(account);
		}
	}

	@Test
	public void testFindOne() {
		// 3. 执行方法
		Account account = accountService.findAccountById(3);
		System.out.println(account);
	}

	@Test
	public void testSave() {
		// 1. 使用注解方式，获取Ioc容器										// 参数为使用了@Configuration注解的类的Class对象，也就是配置类
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		// 2. 获取Service对象
		IAccountService accountService = applicationContext.getBean("accountService", IAccountService.class);
		// 3. 执行方法
		Account account = new Account();
		account.setName("shannain");
		account.setMoney(8000f);
		accountService.saveAccount(account);
	}

	@Test
	public void testUpdate() {
		// 1. 使用注解方式，获取Ioc容器										// 参数为使用了@Configuration注解的类的Class对象，也就是配置类
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		// 2. 获取Service对象
		IAccountService accountService = applicationContext.getBean("accountService", IAccountService.class);
		// 3. 执行方法
		Account account = new Account();
		account.setId(1);
		account.setName("tutu");
		account.setMoney(5000f);
		accountService.updateAccount(account);
	}

	@Test
	public void testDelete() {
		// 1. 使用注解方式，获取Ioc容器										// 参数为使用了@Configuration注解的类的Class对象，也就是配置类
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		// 2. 获取Service对象
		IAccountService accountService = applicationContext.getBean("accountService", IAccountService.class);
		// 3. 执行方法
		accountService.deleteAccount(3);
	}
}
