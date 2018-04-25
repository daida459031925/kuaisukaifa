package bean.service;

import BasicDao.BasicDAO;
import bean.Entity.UserEntity;
import bean.Mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * BasicDao 这个是所有的基础公共类
 * 这个父类内容在代码中写入的是
 * 增删查改，而传入的一个借口是自己实现的内容
 */

@Service//相当于原来的service层
@Transactional(rollbackFor = Exception.class)//事物管理
public class UserService extends BasicDAO<UserMapper,UserEntity> {

    public List<UserEntity> getUserMapperList(){
        return mapper.getUserMapperList();
    }

}
