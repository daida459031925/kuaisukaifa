package bean.service.WeService.WeServiceImpl;

import bean.Dao.WeUserDao;
import bean.Entity.WeUserEntity;
import bean.service.PublicService.RedisSerice;
import bean.service.WeService.WeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeUserServiceImpl extends RedisSerice implements WeUserService{

    @Autowired
    private WeUserDao weUserDao;

    public int add(WeUserEntity weUserEntity){
        int insert=weUserDao.insert(weUserEntity);
        return insert;
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
