package User;

import java.util.List;

public interface UserSerice {

    public int add(ShiTilei shitilei);//添加
    public int del(String id);//删除

    public List<ShiTilei> fandAll();
    public ShiTilei fand(String id,String pws);

    public List<ShiTilei> sort(String name);
}
