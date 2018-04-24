package bean;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
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
@ServletComponentScan(basePackages = "Filter")//简单来说就是过滤器使用添加这个注解生效并且指定包名。
//使用嵌入式容器时，可以使用@ServletComponentScan
//启用@WebServlet，@ WebFilter和@WebListener注释类的自动注册。
//如果使用外置容器的话，容器的内置发现机制将会被使用，而不需要使用这条注解。
@ComponentScan(basePackages = {"bean","config"})//这个注解的作用是指定扫描包，默认扫描的时候是当前这个类的包对应的子包的所有类
//当写了的时候需要注意basePackages = {"bean","config"} 和数组一样对需要扫描的包进行赋值，这个样子就可以自定义包的存放位置.
//当添加了ComponentScan这个以后必须需要指定你的主包，然后一次添加需要扫描的附包，不然就无法扫描到则无法运行.
//@ImportResource("/xx/xx.xml")//导入指定xml此功能没有探究
@MapperScan(basePackages = {"bean.Mapper"})//指定扫描包,这样就不用去每一个类中添加接口扫描
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
