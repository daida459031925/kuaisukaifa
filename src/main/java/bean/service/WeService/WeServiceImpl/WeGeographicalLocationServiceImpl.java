package bean.service.WeService.WeServiceImpl;

import bean.Dao.WeGeographicalLocationDao;
import bean.Entity.WeCheckInEntity;
import bean.service.PublicService.RedisSerice;
import bean.service.WeService.WeGeographicalLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tool.PublicData0_23;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class WeGeographicalLocationServiceImpl extends RedisSerice implements WeGeographicalLocationService{
    @Autowired
    private WeGeographicalLocationDao weGeographicalLocationDao;

    @Override
    public int add(WeCheckInEntity weCheckInEntity) throws ClassNotFoundException {
        Example example = new Example(WeCheckInEntity.class);
        Example.Criteria criteria=example.createCriteria();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        criteria.andBetween("add_time", sdf.format(PublicData0_23.getStartTime()).toString(),sdf.format(PublicData0_23.getnowEndTime()).toString());//在当前时间内是否有签到信息
        criteria.andEqualTo("openid",redis_tool.get(weCheckInEntity.getOpenid()).toString());
        WeCheckInEntity weCheckIn=weGeographicalLocationDao.selectOneByExample(example);
        if(weCheckIn != null){
            return 0;
        }
        weCheckInEntity.setAdd_time(new Date());
        weCheckInEntity.setOpenid(redis_tool.get(weCheckInEntity.getOpenid()).toString());
        int count=weGeographicalLocationDao.insert(weCheckInEntity);
        return count;
    }

    @Override
    public int del(WeCheckInEntity weCheckInEntity) {
        return 0;
    }

    @Override
    public int up(WeCheckInEntity weCheckInEntity) {
        return 0;
    }

    @Override
    public WeCheckInEntity selectKEY(WeCheckInEntity weCheckInEntity, String... strings) throws ClassNotFoundException {
        return null;
    }

    @Override
    public List<WeCheckInEntity> selectAllKEY(WeCheckInEntity weCheckInEntity, String... strings) throws ClassNotFoundException {
        weCheckInEntity.setOpenid(redis_tool.get(weCheckInEntity.getOpenid()).toString());
        Example example = new Example(WeCheckInEntity.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("openid",weCheckInEntity.getOpenid());
        example.orderBy("add_time");
        return weGeographicalLocationDao.selectByExample(example);
    }
}
