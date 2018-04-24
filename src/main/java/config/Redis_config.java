package config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


import java.lang.reflect.Method;

/**
 * spring boot中集成主要是需要写java类然后继承CachingConfigurerSupport
 * 来实现Redis数据缓存
 * 要让java把这个类看成配置类需要注解@Configuration
 *
 *
 * 这个方法不知道为什么自己按照网上写的配置是无法运行的——带研究
 */
@Configuration//这个注解的意思是java配置类
@EnableCaching//这个类是缓存配置类
//@PropertySource(value = {"application-redis.properties"})//此注解主要作用时实现指定properties的文件导入
public class Redis_config extends CachingConfigurerSupport{


    //读取配置application文件中的key直接使用Value
    //import org.springframework.beans.factory.annotation.Value;
    //注意是这个包
    @Value(value = "${spring.redis.timeout}")
    public Long timeOut;//依赖注入的时候不能用static


    /**
     * 自定义缓存数据key 生成策略bean包+类名+方法名+所有参数
     */
/*注意这个方法相当于以前的配置文件内容所以需要bean注解*/
    @Bean
    public KeyGenerator/*需要的是org.springframework.cache.interceptor.KeyGenerator;*/
    wiselyKeyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                /*需要重写父类的方法默认情况下自定义策略是返回空的*/
                /*这里使用到了java反射的知识*/
                StringBuilder sb = new StringBuilder();//创建字符串
                sb.append(target.getClass().getName());//添加类名
                sb.append(method.getName());//添加方法名
                for (Object obj : params) {//添加包名
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };

    }



    /**要启用spring缓存支持,需创建一个 CacheManager的 bean，CacheManager
    接口有很多实现，这里Redis 的集成，用 RedisCacheManager这个实现类
        Redis 不是应用的共享内存，它只是一个内存服务器，就像 MySql 似的，
        我们需要将应用连接到它并使用某种“语言”进行交互，因此我们还需要
        一个连接工厂以及一个 Spring 和 Redis 对话要用的 RedisTemplate，
        这些都是 Redis 缓存所必需的配置，把它们都放在自定义的 CachingConfigurerSupport 中
         */
    @Bean
    public CacheManager cacheManager(
            @SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(timeOut);//设置缓存保留时间（seconds）
        return cacheManager;
    }


    //1.项目启动时此方法先被注册成bean被spring管理
   //这个无法实现还是技术不到位目前没有实现成功
    /* @Bean
    public RedisTemplate<String, String> redisTemplate(
            RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =
                new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }*/

    /**
     * 解释：SpringBoot提供了对Redis的自动配置功能，
     * 在RedisAutoConfiguration中默认为我们配置了
     * JedisConnectionFactory（客户端连接）、
     * RedisTemplate以及StringRedisTemplate（数据操作模板），
     * 其中StringRedisTemplate模板只针对键值对都是字符型的数据进行操作，
     * 本示例采用RedisTemplate作为数据操作模板，
     * 该模板默认采用JdkSerializationRedisSerializer的二进制数据序列化方式，
     * 为了方便演示本示例采用Jackson2JsonRedisSerializer来序列化和反序列化
     * redis的value值，使用StringRedisSerializer来序列化和反序列化redis的key值。
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory/*这个地方不知道为什么一直报错*/) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);

        // 开启事务支持
        template.setEnableTransactionSupport(true);

        template.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
}
