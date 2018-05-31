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

@Service
public class WeUserServiceImpl extends RedisSerice implements WeUserService{

    @Autowired
    private WeUserDao weUserDao;

    public int add(WeUserEntity weUserEntity) throws ClassNotFoundException {
        String key= Redis_tool.getBaoming_Classname_fangfaming(this,weUserEntity.getOpenid());
        if(redis_tool.exists(key)){
            /**
             * 如果存在在redis中的话就直接返回0证明没有添加到数据库中
             */
            System.err.println("这里测试缓存机制内容***********************************");
            return 0;
        }
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
        ExampleTool.getExample(PropertiesTool.getProperties("WeUserEntity"));
//        Example example=new Example(klass);
//        example
//        WeUserEntity WeUser=weUserDao.selectOneByExample();
        weUserEntity.setAdd_time(new Date());
//        int insert=weUserDao.insert(weUserEntity);
        return 2;
    }

    @Override
    public int del(WeUserEntity weUserEntity) {
        return 0;
    }

    @Override
    public int up(WeUserEntity weUserEntity) {
        return 0;
    }

}
