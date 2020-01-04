package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author Lian Flower
 * @Date 2019/9/16 10:24
 * @Version 1.0
 */
@Configuration
@ComponentScan({"com.itheima"})
@EnableAspectJAutoProxy
public class SpringConfiguration {
}
