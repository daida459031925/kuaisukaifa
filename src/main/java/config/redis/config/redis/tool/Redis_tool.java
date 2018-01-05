package config.redis.config.redis.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 这个类实现redis的简单工具方法，高级的目前看不懂也不会
 *
 */
@Component
public class Redis_tool {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @Description: 批量删除缓存
     * @param keys
     */
    public void remove(final String... keys){
        for(String key :keys){
            remove(key);
        }
    }

    /**
     * @Description: 批量删除缓存(通配符)key
     * @param pattern
     */
    public void removePattern(final String pattern){
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if(keys.size()>0) redisTemplate.delete(keys);
    }

    /**
     * @Description: 删除缓存
     * @param key
     */
    public void remove(final String key){
        if(exists(key)){
            redisTemplate.delete(key);
        }
    }

    /**
     * @Description: 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public boolean exists(final String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * @Description: 读取缓存
     * @param key
     * @return
     */
    public Object get(final String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * @Description: 写入缓存
     * @param key
     * @param value
     * @return
     */
    public void set(final String key,Object value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * @Description: 写入缓存(可以配置过期时间)
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public void set(final String key,Object value,Long expireTime){
        redisTemplate.opsForValue().set(key,value,expireTime, TimeUnit.SECONDS);
        //或者调用这个
        //redisTemplate.opsForValue().set(key, value);
        //redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 哈希添加
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmSet(String key,Object hashKey,Object value){
        HashOperations<String,Object,Object> hash=redisTemplate.opsForHash();
        hash.put(key,hashKey,value);
    }


    /**
     * 获取哈希数据
     * @param key
     * @param hashKey
     * @return
     */
    public Object hmGet(String key,Object hashKey){
        HashOperations<String,Object,Object> hash=redisTemplate.opsForHash();
        return hash.get(key,hashKey);
    }

    /**
     * 添加列表
     * @param k
     * @param v
     */
    public void lpush(String k,Object v){
        ListOperations<String,Object> list = redisTemplate.opsForList();
        list.rightPush(k,v);
    }

    /**
     * 列表获取
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<Object> lRange(String k,long l,long l1){
        ListOperations<String,Object> list=redisTemplate.opsForList();
        return list.range(k,l,l1);
    }


    /**
     * 添加集合
     * @param key
     * @param value
     */
    public void add(String key,Object value){
        SetOperations<String,Object> set = redisTemplate.opsForSet();
        set.add(key,value);
    }


    /**
     * 获取集合
     * @param key
     * @return
     */
    public Set<Object> setMembers(String key){
        SetOperations<String,Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 有序集合添加
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key,Object value,double scoure){
        ZSetOperations<String,Object> zset = redisTemplate.opsForZSet();
        zset.add(key,value,scoure);
    }

    /**
     * 有序集合获取
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key,double scoure,double scoure1){
        ZSetOperations<String,Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key,scoure,scoure1);
    }
}
