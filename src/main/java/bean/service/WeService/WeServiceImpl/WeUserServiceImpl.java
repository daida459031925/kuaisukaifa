package bean.service.WeService.WeServiceImpl;

import bean.Dao.WeUserDao;
import bean.Entity.WeUserEntity;
import bean.PublicLog;
import bean.service.PublicService.RedisSerice;
import bean.service.WeService.WeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class WeUserServiceImpl extends RedisSerice implements WeUserService, PublicLog{

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
            String key = weUser.getOpenid();
            if(redis_tool.exists(key)){
                //如果存在就将user中的object里的对象设置为需要传输的UUID
                System.err.println("这里测试缓存机制内容***********************************");
                weUser.setObject(redis_tool.get(key));
                logger.info(weUser);
                return weUser;
            }
            //如果不是或者没有那么将生产出来的uuid赋值给redis
            //出现一个redis问题就是当openid消失的时候，但是这个时候存在未消失的session_3rd目前最长为10分钟，并且不会影响用户使用
            //但是这十分钟内如果数据量大的话，那就会出现一个在redis中大量冗余数据以及内存的超负荷
            //并且这个就是十分钟。也就是说10个人要占有20个人的内存。在大数据方向需要节省服务器价格呀，内存很贵的。
            redis_tool.set(key,uuid,timeOut);
            redis_tool.set(uuid,weUserEntity.getOpenid(),timeOut);
            weUser.setObject(redis_tool.get(key));
        }
        return weUser;
    }

    @Override
    public List<WeUserEntity> selectAllKEY(WeUserEntity weUserEntity, String... strings) throws ClassNotFoundException {
        return null;
    }

}
