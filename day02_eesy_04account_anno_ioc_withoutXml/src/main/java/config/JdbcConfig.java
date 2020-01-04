package config;

/**
 * @Author Lian Flower
 * @Date 2019/9/14 19:47
 * @Version 1.0
 */

import com.itheima.service.IAccountService;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * 连接数据库的配置类
 *
 * 		注意：如果在方法上使用了注解，那么要确保该方法所在的类会被spring扫描到，不然spring不会扫描方法上的注解。
 * 				这是为什么呢？
 * 					因为当我们告知spring去扫描某些包下的类时，前提是这些类上面必须有spring的注解，如果没有，则spring不会扫描这些类，
 * 						自然也就不会扫描类中的方法上的注解
 * 					那如果不想在类上使用@Service、@Component、@Controller、@Repository这些注解，怎么办呢，
 * 						因为加上这些注解后，就会将该类的对象存入到spring容器中，而这些对象可能不是我们所需要的，
 * 							1. 对于这种情况，则可以使用@Configuration注解，即将当前类作为一个配置类，这样当前类的对象不会被存入到spring容器，
 * 								并且类中的方法上的注解也能被扫描到								（需要告知spring容器当前类所在的包）
 * 							2. 或者是，在创建容器时将当前类的Class对象作为参数传给AnnotationConfigApplicationContext对象，
 * 								这样	spring就会把这个类看作是配置类，就会去扫描这这个类，以及扫描类中方法上的注解	（不需要告知spring容器当前类所在的包）
 *							3. 还有一种方法，就是在主配置类上加上@Import注解，@Import可以指定其它类为配置类，
 *								所以可以在主配置类上使用Import指定当前类为配置类，然后spring会自动去扫描@Import指定的配置类，
 *									以及扫描类中方法上的注解			（不需要告知spring容器当前类所在的包）
 *								主配置类：就是创建容器时，传给AnnotationConfigApplicationContext对象的Class对象所对应的类
 */
@Configuration
public class JdbcConfig {

	// @Value注解可以注入基本类型和String类型的数据
	// value属性用于指定数据的值，可以使用spring的SpEL表达式(也就是spring的el表达式)
	// SpEL的写法：${表达式}

	// 使用@Value注解为基本类型和String注入数据
	@Value("${jdbc.driver}")
	private String driver;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

	/**
	 * 创建一个QueryRunner对象
	 * @param dataSource
	 * @return
	 */
	@Bean(name = "runner")
	@Scope("prototype")	// 将QueryRunner对象设置为多例对象
	public QueryRunner createQueryRunner(@Qualifier("dataSource2") DataSource dataSource) {
		return new QueryRunner(dataSource);	// 根据参数的数据类型，到Ioc容器中查找类型相同的bean对象，并且bean的id为@Qualifier指定的id
	}

	/**
	 * 创建数据源对象（连接池）
	 * @return
	 */
	@Bean(name = "dataSource2")
	public DataSource createDataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(driver);
			dataSource.setJdbcUrl(url);
			dataSource.setUser(username);
			dataSource.setPassword(password);
			return dataSource;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Bean(name = "dataSource")
	public DataSource createDataSource2() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(driver);
			dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/eesy02?characterEncoding=utf8&amp;useSSL=false");
			dataSource.setUser(username);
			dataSource.setPassword(password);
			return dataSource;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

/*
总结：
	我们会发现，在使用纯注解的方式配置Ioc容器时，并没有很省事，反而还有点繁琐，
				因为我们需要创建配置类，然后对于存在于jar包中的对象（例如QueryRunner），（我们无法更改jar包中的代码，所以也就不能在jar包的类上直接添加注解）
					还需要创建一个方法，在方法中new一个QueryRunner对象，然后通过返回值将QueryRunner对象存入到Ioc容器中。
						如果需要给QueryRunner对象传入DataSource对象作为参数，则还需要再创建一个方法，new一个DataSource对象，
							然后再将通过返回值将DataSource对象存入Ioc容器，这样QueryRunner对象才能将DataSource对象作为参数
				而如果使用xml方式，则就会方便一点，只需要在bean标签中配置一下QueryRunner对象就行了，
					如果需要传入DataSource对象作为参数，则可以通过有参构造函数或set方法注入。比使用注解方便

				不过注解配置也有它的优点，对于我们自己编写的类，如果想将该类的对象存入Ioc容器，
					则只需要在该类上加上注解就可以了，比较方便
						而如果使用xml，则还要在bean标签中配置编写的类，没有注解方便

				所以，对于自己编写的类，使用注解比较方便
						对于存在于jar包中的类，使用xml比较方便

				在公司实际开发中，如果可以自主选择，那么建议xml方式和注解方式混用
 */