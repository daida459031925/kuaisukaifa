package bean.User;

import bean.exception.*;
import com.github.pagehelper.Page;

import java.util.List;

public interface UserSerice {

    public int add(ShiTilei shitilei)throws idException,nameException,dianhuaException,xingbeiException,shenfenzhengException,pwsException;//添加
    public int del(String id)throws idException;//删除

    public Page<ShiTilei> fandAll(int pageNO, int pageSize);
    public ShiTilei fand(String id,String pws) throws idException,pwsException;

    public List<ShiTilei> sort(String name) throws nameException;
}
