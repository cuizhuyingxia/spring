package com.itheima.service.impl;

		import com.itheima.dao.IAccountDao;
		import com.itheima.dao.impl.AccountDaoImpl;
		import com.itheima.service.IAccountService;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.beans.factory.annotation.Qualifier;
		import org.springframework.context.annotation.ImportResource;
		import org.springframework.context.annotation.Scope;
		import org.springframework.stereotype.Component;
		import org.springframework.stereotype.Controller;
		import org.springframework.stereotype.Repository;
		import org.springframework.stereotype.Service;

		import javax.annotation.PostConstruct;
		import javax.annotation.PreDestroy;
		import javax.annotation.Resource;

/**
 * @Author Lian Flower
 * @Date 2019/9/12 12:50
 * @Version 1.0
 */

/**
 * 账户的业务层实现类
 * 		xml方式配置
 * 			<bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"
 * 				scope="" init-method="" destroy-method="">
 * 				<property name="" value=""></property>
 * 			</bean>
 *
 * 		使用注解配置
 * 			用于创建对象的注解：和在xml配置文件中编写一个<bean>标签的作用是一样的
 * 				@Component
 * 					作用：使用反射创建当前类的对象，并存入spring容器中
 * 					属性：
 * 						value：用于指定bean的id。如果不写，默认值为当前类名，且首字母小写
 *				@Controller		一般用在表现层
 *				@Service		一般用在业务层
 *				@Repository		一般用在持久层
 *				以上三个注解，它们的作用和属性与@Component是一样的。
 *				它们三个是spring框架专门为三层架构提供的注解，使得三层架构更加清晰
 * 			用于注入数据的注解：和在xml配置文件中的bean标签中写一个<property>标签的作用是一样的
 * 				@Autowired
 * 					作用：自动按照数据类型注入，只要容器中有唯一的一个bean对象的类型和要注入的类型匹配，就可以注入成功
 * 							如果没有相匹配的bean对象，则失败
 * 								如果容器中有多个相同类型的bean对象，则会根据数据类型的变量名称，在容器中查找是否有id等于变量名称的，
 * 									如果有，则可以注入成功	，否则，注入失败
 * 					出现位置：可以是变量上，也可以是方法上
 * 				@Qualifier
 * 					作用：根据数据类型，到Ioc容器中查找类型相同的bean对象，并且bean的id为@Qualifier指定的id
 * 							它在给类成员注入时，需要和@Autowired一块使用；但是在给方法参数注入时，可以单独使用
 * 					属性：
 * 						value：指定要注入的bean的id
 *				@Resource
 *					作用：直接按照指定的bean的id注入，它可以独立使用
 *					属性：
 *						name：指定要注入的bean的id
 *				以上三个注解都只能注入其它bean类型（在配置文件中配置或使用注解配置的bean）的数据，而基本类型和String类型无法使用上述注解注入
 *				另外，集合类型只能通过xml方式实现注入
 *				@Value
 *					作用：用于注入基本类型和String类型的数据
 *					属性：
 *						value：用于指定数据的值，它可以使用spring中的SpEL(也就是spring的el表达式)
 *								SpEL的写法：${表达式}
 * 			用于改变作用范围的注解：和在xml配置文件中的bean标签中使用scope属性实现的作用是一样的
 * 				@Scope
 * 					作用：用于指定bean的作用范围
 * 					属性：
 * 						value：指定范围的取值。常用取值有：singleton	prototype
 * 			和生命周期相关的注解：和在xml配置文件中使用init-method和destroy-method的作用是一样的
 * 				@PostConstruct
 * 					作用：用于指定初始化方法
 * 				@PreDestroy
 * 					作用：用于指定销毁方法
 */
@Service("accountService")
//@Scope("prototype")
@Scope("singleton")
public class AccountServiceImpl implements IAccountService {

	// 例如当前数据类型为IAccountDao，如果Ioc容器中一个bean对象的数据类型为IAccountDao，就可以注入成功。
	// 或者是有一个bean对象的类型为AccountDaoImpl，也可以注入成功，因为AccountDaoImpl继承了IAccountDao接口，
	// 所以可以将AccountDaoImpl看成是AccountDao类型
	// @Autowired()
	// private IAccountDao accountDao1 = null;
	// 例如，当前容器中有两个bean对象都是AccountDaoImpl类型，那么则会根据数据类型的变量名称accountDao1，
	// 在容器中查找是否有id等于该变量名称，如果有，则可以注入成功，否则注入失败


	// 根据变量的数据类型，到Ioc容器中查找类型相同的bean对象，并且bean的id为@Qualifier指定的id
	//@Autowired
	//@Qualifier("accountDao1")
	//private IAccountDao accountDao = null;

	//直接到Ioc容器中查找是否有id为name属性指定的id的bean对象
	@Resource(name = "accountDao2")
	private IAccountDao accountDao;

	@PostConstruct
	public void init() {
		System.out.println("对象被创建了");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("对象被销毁了");
	}

	/**
	 * 模拟保存账户
	 */
	public void saveAccount() {
		accountDao.saveAccount();
	}
}
