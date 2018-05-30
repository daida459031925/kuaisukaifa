package bean.service.PublicService;

import config.redis.tool.Redis_tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisSerice {

    @Autowired
    protected RedisTemplate redisTemplate;//可以直接使用这个或者使用自己封装的工具类

    @Autowired
    protected Redis_tool redis_tool;

    @Value(value = "${spring.redis.timeout}")
    protected Long timeOut;//依赖注入的时候不能用static
}
