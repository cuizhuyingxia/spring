import com.itheima.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author Lian Flower
 * @Date 2019/9/15 20:05
 * @Version 1.0
 */
public class AOPTest {

	public static void main(String[] args) {
		// 1. 获取容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
		// 2. 获取对象
		IAccountService accountService = applicationContext.getBean("accountService", IAccountService.class);
		// 3. 执行方法
		accountService.saveAccount();
		accountService.updateAccount(1);
		accountService.deleteAccount();
	}
}
