package bean.User;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    public int add(ShiTilei shitilei);//添加
    public int del(String id);//删除

    public List<ShiTilei> fandAll();
    public ShiTilei fand(String id);

    public List<ShiTilei> sort(String name);

}
