package bean.service.ServiceImpl;


import bean.Dao.UserDao;
import bean.Entity.UserEntity;
import bean.exception.*;
import bean.service.UserSerice;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import config.redis.config.redis.tool.Redis_tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tool.MD5;
import tool.dianhuayanzheng;
import tool.mimafuzhadu;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
//@PropertySource(value = {"application-redis.properties"})//此注解主要作用时实现指定properties的文件导入
public class UserSericeImpl implements UserSerice {
    @Autowired
    private UserDao userDao;//不知道为什么会一直报错,但是不影响使用

    @Autowired
    private RedisTemplate redisTemplate;//可以直接使用这个或者使用自己封装的工具类

    @Autowired
    private Redis_tool redis_tool;

    @Value(value = "${spring.redis.timeout}")
    public Long timeOut;//依赖注入的时候不能用static

    /**
     *
     * @param shitilei
     * @return
     * @throws idException
     * @throws nameException
     * @throws dianhuaException
     * @throws xingbeiException
     * @throws shenfenzhengException
     * @throws pwsException
     * 异常信息必须用RuntimeException的子类，将继承的子类异常抛出到Controller 然后在类中继承AbstractController将所有异常
     * 信息进行集中管理
     */
    @Override
    public int add(UserEntity shitilei) /*throws
            idException,nameException,dianhuaException,xingbeiException,shenfenzhengException,pwsException*/{
//        private String id;//用户id  唯一标识符
//        private String name;//用户名字
//        private Timestamp Date;//创建时间
//        private String dianhua;//电话
//        private String xingbei;//性别
//        private String shenfenzheng;//身份证
//        private String pws;//密码
        if(shitilei.getId()==null || shitilei.getId().trim().isEmpty()){
            throw new idException("账号不能为空");
        }
        if(shitilei.getName()==null || shitilei.getName().trim().isEmpty()){
            throw new nameException("姓名不能为空");
        }
        if(shitilei.getDianhua()==null || shitilei.getDianhua().trim().isEmpty()){
            throw new dianhuaException("电话不能为空");
        }else{
            if(shitilei.getDianhua().length()==11 && !"0".equals(shitilei.getDianhua().substring(0,1))){
                if(dianhuayanzheng.isMobile(shitilei.getDianhua())){
                }else{
                    throw new dianhuaException("手机格式不对");
                }
            }else{
                if(dianhuayanzheng.isPhone(shitilei.getDianhua())){
                }else{
                    throw new dianhuaException("固话格式不对");
                }
            }
        }
        if(shitilei.getXingbei()==null || shitilei.getXingbei().trim().isEmpty()){
            throw new xingbeiException("性别不能为空");
        }
        if(shitilei.getShenfenzheng()==null || shitilei.getShenfenzheng().trim().isEmpty()){
            throw new shenfenzhengException("身份证不能为空");
            //暂时为任意填写
        }
        if(shitilei.getPws()==null || shitilei.getPws().trim().isEmpty()){
            throw new pwsException("密码不能为空");
        }
        if(mimafuzhadu.validPwd(shitilei.getPws())){
            throw new pwsException("密码复杂度不够");
        }else{
            //进行MD5加密
            shitilei.setPws(MD5.encode(shitilei.getPws()));;
        }
        if(shitilei.getDate()==null){
         shitilei.setDate(new Timestamp(new Date().getTime()));//如果未传入时间则以服务器时间为准
        }
        //在此可以添加验证逻辑
        int i=userDao.add(shitilei);
        return i;
    }

    @Override
    public int del(String id) /*throws idException*/{
        if(id==null || id.trim().isEmpty()){
            //返还错误信息
            throw new idException("没有账号无法删除");
        }
        int i=userDao.del(id);
        return i;
    }


    /**
     * redis_config这个类是其中一种高级的方式来去管理redis目前没有成功
     * 简单的方式来实现redis管理方式是以下示例
     * @param pageNO
     * @param pageSize
     * @return
     */
    @Override
    public Page<UserEntity> fandAll(int pageNO, int pageSize) {
//        String key = "iiiiii";//这个地方为了简单直接写了KEY的值，实际上的架构应该实现或者创建一个类里面全部存放常量来进行同一管理，或者来实现配置文件来管理常量
//        ValueOperations<String,Page<UserEntity>> s=redisTemplate.opsForValue();//使用redisTemplate和jdbcTemplate差不多而spring boot以及自动集成了redisTemplate所以只需要在application中配置好就可以直接使用
//        if(redisTemplate.hasKey(key)){//这个位置是来判断redisTemplate中是否存在这个KEY不存在的话就走下面调用数据库，存在的话就直接返还，这样就可以节省下来很多数据查询时间
//            System.err.println("这里测试缓存机制内容***********************************");
//            return s.get(key);
//        }
        ///*添加方法名，由于方法名和jvm有关所以还是开发者来维护默认填写自己的方法名或者添加其他参数*/
        //由于获取方法名比较耗时所以直接由编写代码的人进行维护
        String key=Redis_tool.getBaoming_Classname_fangfaming(this,"fandAll");
        if(redis_tool.exists(key)){
            System.err.println("这里测试缓存机制内容***********************************");
            return (Page<UserEntity>) redis_tool.get(key);
        }

        PageHelper.startPage(pageNO,pageSize);
        /**使用com.github.pagehelper.PageHelper 的包实现sql重写自动添加分页，然后自己在sql里添加过滤条件*/
        Page<UserEntity> user=userDao.fandAll();
        redis_tool.set(key,user,timeOut);
//        s.set(key,user,60/*保存多少秒*/, TimeUnit.SECONDS/*单位是秒*/);//这个位置是保存KEY，保存数据，保存时间，Time的单位.内容很多这个是简单操作
        return user;
    }

    @Override
    public UserEntity fand(String id, String pws) /*throws idException,pwsException*/{
        if(id==null || id.trim().isEmpty()){
            //异常处理
            throw new idException("账号为空");
        }
        if(pws==null || pws.trim().isEmpty()){
            //异常处理
            throw new pwsException("密码为空");
        }
        UserEntity user=userDao.fand(id);
        if(user==null){
            //账号错误
            throw new idException("账号错误");
        }
        if(!pws.equals(user.getPws())){
            //密码错误
            throw new pwsException("密码错误");
        }
        return user;
    }

    @Override
    public List<UserEntity> sort(String name) /*throws nameException*/{
        if(name==null || name.trim().isEmpty()){
            throw new nameException("参数为空");
        }
        List<UserEntity> user=userDao.sort(name);
        return user;
    }
}
