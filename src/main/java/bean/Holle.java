package bean;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller//这是spring mvc处理请求信息层面 //这个主类必须在一个指定的包下 当时非java资源包下
//idea的可以直接还原版本  将项目点击右键查看项目文件以及时间 show history  还原
@SpringBootApplication//spring boot核心注解（主要目的是开启自动配置）
/**
 * SpringBootApplication里有开启默认配置 加载spring-boot-starter-web依赖以后
 * 就会自动配置tomcat和SpringMVC
 * @ComponentScan：默认扫描@SpringBootApplication所在类的同级目录以及它的子目录。
 * @SpringBootApplication(exclude = {RedisAutoConfiguration.class,RedisAutoConfiguration.class})
*/
//@Configuration//用@Configuration注解该类，等价 与XML中配置beans；相当于xml
// 用@Bean标注方法等价于XML中配置bean。
//@SpringBootConfiguration//在Spring Boot项目中推荐使用@ SpringBootConfiguration替代@Configuration
public class Holle extends SpringBootServletInitializer {//返回jsp页面必须继承SpringBootServletInitializer类重写里面的方法
    private static Logger logger = Logger.getLogger(Holle.class);//打印log日志
    public static void main(String[] args) {
        SpringApplication.run(Holle.class);//启动项目时候直接在main方法run一下
    }

    @RequestMapping("/hello")//请求注解
//    @ResponseBody//返还json对象的Spring mvc注解
    public String hello(){
        //返回的页面内容，在yml或者properties那个配置文件中
        //配置后可以返回别的页面或者jsp
        System.out.println("************************************");
        return "hello";
    }


    /**
     *
     　　 新建一个SpringBootStartApplication 继承自 SpringBootServletInitializer

     作用：因为在外部容器部署的话，就不能依赖于Application的main函数了，而是要以类似于web.xml文件配置的方式来启动Spring应用上下文，

     　　　 此时我们需要在启动类中继承SpringBootServletInitializer并实现configure方法，这个类的作用与在web.xml中配置负责初始化Spring应用上下文的监听器作用类似
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Holle.class);
    }

}
