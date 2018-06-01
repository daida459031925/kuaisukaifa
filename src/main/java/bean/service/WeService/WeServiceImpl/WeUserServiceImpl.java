package bean.service.WeService.WeServiceImpl;

import bean.Dao.WeUserDao;
import bean.Entity.WeUserEntity;
import bean.service.PublicService.RedisSerice;
import bean.service.WeService.WeUserService;
import config.redis.tool.Redis_tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tool.ExampleTool;
import tool.PropertiesTool;

import java.util.Date;
import java.util.List;

@Service
public class WeUserServiceImpl extends RedisSerice implements WeUserService{

    @Autowired
    private WeUserDao weUserDao;

    public int add(WeUserEntity weUserEntity) throws ClassNotFoundException {
//        String key= Redis_tool.getBaoming_Classname_fangfaming(this,weUserEntity.getOpenid());
//        if(redis_tool.exists(key)){
            /**
             * 如果存在在redis中的话就直接返回0证明没有添加到数据库中
             */
//            System.err.println("这里测试缓存机制内容***********************************");
//            return 0;
//        }
        /**
         * 如果redis中没有有的话
         * 1.首先执行查找
         * 如果没有就添加到数据库  如果有就吧这个数据保存到redis中
         */
        /**
         * 通用mapper中需要传入条件查询或者做别的事的时候需要使用到mybatis
         * 这时候需要创建一个Example对象这个对象来进行条件插入
         * 这个是hibernate一样
         */
        /**
         * 通过java反射获取到Example对象
         */
//        Example example=ExampleTool.getExample(PropertiesTool.getProperties("WeUserEntity"));
//        Example.Criteria criteria=example.createCriteria();
//        criteria.andEqualTo("openid",weUserEntity.getOpenid());//这里面的东西可以集成写成完成的工具类 写好的前辈的项目ag_admin
//        WeUserEntity WeUser=weUserDao.selectOneByExample(example);
//        int insert=0;
//        if(WeUser==null){
            //还没有做数据验证
            weUserEntity.setAdd_time(new Date());
            int insert=weUserDao.insert(weUserEntity);
//        }
        return insert;
    }

    @Override
    public int del(WeUserEntity weUserEntity) {
        return 0;
    }

    @Override
    public int up(WeUserEntity weUserEntity) {
        weUserEntity.setUp_time(new Date());
        int up=weUserDao.updateByPrimaryKey(weUserEntity);
        return up;
    }

    @Override
    public WeUserEntity selectKEY(WeUserEntity weUserEntity,String... string) throws ClassNotFoundException {
        WeUserEntity weUser=weUserDao.selectByPrimaryKey(weUserEntity.getOpenid());
        String uuid = "";
        for (String uuids:string){
            uuid = uuids;
        }
        if( weUser==null){
            //如果等于null那么就需要把意外情况给前端  需要让他重新发送或者点击绑定
        }else{
            //数据库中存在
            String key=Redis_tool.getBaoming_Classname_fangfaming(this,weUser.getOpenid());
            if(redis_tool.exists(key)){
                //如果存在就将user中的object里的对象设置为需要传输的UUID
                System.err.println("这里测试缓存机制内容***********************************");
                weUser.setObject(redis_tool.get(key));
                return weUser;
            }
            //如果不是或者没有那么将生产出来的uuid赋值给redis
            redis_tool.set(key,uuid,timeOut);
            weUser.setObject(redis_tool.get(key));
        }
        return weUser;
    }

}
