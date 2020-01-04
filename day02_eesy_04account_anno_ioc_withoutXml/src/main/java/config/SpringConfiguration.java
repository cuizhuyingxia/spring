package config;

/**
 * @Author Lian Flower
 * @Date 2019/9/14 18:35
 * @Version 1.0
 */

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import javax.swing.plaf.basic.ComboPopup;

/**
 * 该类是一个配置类，它的作用和bean.xml是一样的
 * 		spring中的新注解：
 * 			@Configuration
 * 				作用：指定当前类是一个配置类，配置类的作用和bean.xml是一样的，相当于一个xml配置文件
 * 				细节：在创建容器时，如果将配置类的Class对象作为参数传给了AnnotationConfigApplicationContext对象，则在配置类上可以不用写@Configuration注解
 * 			@ComponentScan
 * 				作用：指定spring在创建容器时，需要扫描的包
 * 				属性：
 * 					basePackages：指定创建容器时，要扫描的包
 * 					value：和basePackages的功能一样
 * 			@Bean
 * 				作用：用于把当前方法的返回值，作为bean对象，存入spring的Ioc容器中
 * 				属性：
 * 					name：用于指定bean的id。当不写时，默认值为当前方法的名称
 * 				细节：
 * 					当在方法上使用了@Bean注解后，如果方法有参数，那么spring会到容器中查找，（查找策略和@Autowired一样）
 * 						是否有一个bean对象的类型和参数的数据类型相同，如果有，则该参数注入成功，否则注入失败
 * 						如果有多个bean对象的类型都和参数的数据类型相同，则再根据参数名称，在容器中查找有没有id等于参数名称的，
 * 						如果有，则该参数注入成功，否则，注入失败
 * 						注意：如果参数前加上了@Qualifier注解，那么查找规则为：根据参数的数据类型，到Ioc容器中查找类型相同的bean对象，
 * 																		并且bean的id为@Qualifier指定的id
 *			@Import
 *				作用：用于导入其它的配置类
 *				value：用于指定其它配置类的Class对象
 *						Import注解所在的类就是父配置类（主配置类），而导入类的就是子配置类（从配置类）
 *			@PropertySource
 *				作用：用于指定properties文件的位置
 *				属性：
 *					value：指定文件的路径和名称
 *							关键字：classpath，表示文件的路径在类路径下
 */
//@Configuration
@ComponentScan({"com.itheima", "config"})
//@Import(JdbcConfig.class)
@PropertySource("classpath:jdbcConfig.properties")
public class SpringConfiguration {


}


