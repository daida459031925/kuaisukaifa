package tool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;


//想要使用spring boot
//@PropertySource(value = {"changliang.properties"})//在实体类中无法直接用这个来获取properties中对应的属性
//@Component//想要实体类使用上面的properties注入value必须开启扫描，并且在主类中设置（holle中）@ComponentScan(basePackages = {"bean","config","tool"})
//开启后就可以给固定数值赋值；直接使用value就可以。换来的是新问题；在web返还JSON的时候不能new对象 new出来的都是新的；
//@ConfigurationProperties(prefix = "json")

/**
 * 这个类设计思想是实现代码中无直接定义常量，而改用从properties中直接获取参数从而达到不修改代码的情况下来修改配置中的参数；
 * 在properties加载配置文件的时候必须写成对应的配置文件名，在获取的时候也需要写对应的Key名。这个设计缺点吧。
 */

/**
 * 在这个类中注释掉的内容也是可以用的，使用注释掉的内容发现的问题是，依赖注入在别的地方使用的时候，必须使用依赖注入
 * 这样就导致JSON类的只能重载失效，因为依赖注入出来的JSON我目前无法直接使用，也无法智能重载出想要的结果。2018.1.23
 */
public class PublicStaticFinal implements Serializable{

    private static final long serialVersionUID = 1L;

    private static final String PRO= "changliang.properties";
    protected static final String ERROR;
    protected static final String PASS;
    static {
        Properties properties = new Properties();
        try {
            properties.load(PublicStaticFinal.class.getClassLoader().getResourceAsStream(PRO));
            ERROR=properties.getProperty("json.ERROR");
            PASS=properties.getProperty("json.PASS");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("加载changliang.properties异常", e);
        }
    }

//    @Value(value = "${json.ERROR}")
//    @Autowired  //使用这个赋值是无效的
//    @NotNull//检验参数不能为null   import javax.validation.constraints.NotNull;
//    protected String ERROR;

//    @Value(value = "${json.PASS}")
//    @NotNull
//    protected String PASS;


}
