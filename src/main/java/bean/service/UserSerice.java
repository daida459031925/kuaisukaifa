package bean.service;

import bean.Entity.UserEntity;
import com.github.pagehelper.Page;

import java.util.List;

public interface UserSerice {

    public int add(UserEntity shitilei)/*throws idException,nameException,dianhuaException,xingbeiException,shenfenzhengException,pwsException*/;//添加
    public int del(String id)/*throws idException*/;//删除

    public Page<UserEntity> fandAll(int pageNO, int pageSize);
    public UserEntity fand(String id, String pws)/* throws idException,pwsException*/;

    public List<UserEntity> sort(String name) /*throws nameException*/;
}
