package bean.Dao;

import bean.Entity.UserEntity;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserDao {

    public int add(UserEntity shitilei);//添加
    public int del(String id);//删除

    public Page<UserEntity> fandAll();
    public UserEntity fand(String id);

    public List<UserEntity> sort(String name);

}
