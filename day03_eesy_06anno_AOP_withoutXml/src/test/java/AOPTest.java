import com.itheima.service.IAccountService;
import config.SpringConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author Lian Flower
 * @Date 2019/9/15 20:05
 * @Version 1.0
 */
public class AOPTest {

	public static void main(String[] args) {
		// 1. 使用注解方式，获取Ioc容器										// 参数为被@Configuration注解的类的Class对象，也就是配置类
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		// 2. 获取Service对象
		IAccountService accountService = applicationContext.getBean("accountService", IAccountService.class);
		// 3. 执行方法
		accountService.saveAccount();
	}
}
