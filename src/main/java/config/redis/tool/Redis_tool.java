package config.redis.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Method;
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
        redisTemplate.opsForValue().set(key,value);//目前不知道如何配置默认过期时间
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

    public static String getBaoming_Classname_fangfaming(Object This,String... name
     /*添加方法名，由于方法名和jvm有关所以还是开发者来维护默认填写自己的方法名或者添加其他参数*/){
        //System.out.println(This.getClass().getName());//包名.类名
        StringBuilder sb = new StringBuilder();
        sb.append(This.getClass().getName());
        for (String str:name) {
            sb.append(".");
            sb.append(str);
        }
        return sb.toString();
    }


    /**
     * 传入全类名获得对应类中所有方法名和参数名
     */
    @SuppressWarnings("rawtypes")
    private static void getMethodInfo(String pkgName) {
        //这个方法传入完整的包名例如 java.util.HashSet   会得到所有包名以下的方法名
        //但是这个方法效率不高，也不是这么单独使用的
        try {
            Class clazz = Class.forName(pkgName);
            Method[] methods = clazz.getMethods();//import java.lang.reflect.Method;导入这个包
            for (Method method : methods) {
                String methodName = method.getName();
                System.out.println("方法名称:" + methodName);
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (Class<?> clas : parameterTypes) {
                    String parameterName = clas.getName();
                    System.out.println("参数名称:" + parameterName);
                }
                System.out.println("*****************************");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
