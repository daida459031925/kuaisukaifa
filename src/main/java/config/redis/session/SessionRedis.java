package config.redis.session;


import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
//maxInactiveIntervalInSeconds 默认是1800秒过期，这里测试修改为60秒
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=60*3)//这个就是数据存储时间(60一分钟*60一小时)
public class SessionRedis{

}