package bean.Dao;

import BasicDao.BasicDAO;
import bean.Entity.UserEntity;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


//@Mapper//原来用这个实现的是普通的mybatis
public interface UserDao extends BasicDAO<UserEntity>{

    public int add(UserEntity shitilei);//添加
    public int del(String id);//删除

    public Page<UserEntity> fandAll();
    public UserEntity fand(String id);

    public List<UserEntity> sort(String name);

}
