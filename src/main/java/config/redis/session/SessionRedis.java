package config.redis.session;


import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
//maxInactiveIntervalInSeconds 默认是1800秒过期，这里测试修改为60秒
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=60*9)//这个就是数据存储时间(60一分钟*60一小时)//session共享时间是9分钟目前没有制作刷新session的工作
public class SessionRedis{

}