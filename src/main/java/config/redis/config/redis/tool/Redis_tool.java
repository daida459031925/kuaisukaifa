package config.redis.config.redis.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 这个类实现redis的简单工具方法，高级的目前看不懂也不会
 *
 */
@Component
public class Redis_tool {

    @Autowired
    private RedisTemplate redisTemplate;


}
