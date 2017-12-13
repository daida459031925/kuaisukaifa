package User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class UserSericeImpl implements UserSerice {
    @Autowired
    private UserDao userDao;

    @Override
    public int add(ShiTilei shitilei) {
//        private String id;//用户id  唯一标识符
//        private String name;//用户名字
//        private Timestamp Date;//创建时间
//        private String dianhua;//电话
//        private String xingbei;//性别
//        private String shenfenzheng;//身份证
//        private String pws;//密码
        if(shitilei.getId()==null || shitilei.getId().trim().isEmpty()){

        }
        if(shitilei.getName()==null || shitilei.getName().trim().isEmpty()){

        }
        if(shitilei.getDianhua()==null || shitilei.getDianhua().trim().isEmpty()){

        }
        if(shitilei.getXingbei()==null || shitilei.getXingbei().trim().isEmpty()){

        }
        if(shitilei.getShenfenzheng()==null || shitilei.getShenfenzheng().trim().isEmpty()){

        }
        if(shitilei.getPws()==null || shitilei.getPws().trim().isEmpty()){

        }
        if(shitilei.getDate()==null){
         shitilei.setDate(new Timestamp(new Date().getTime()));//如果未传入时间则以服务器时间为准
        }
        int i=userDao.add(shitilei);
        return i;
    }

    @Override
    public int del(String id) {
        if(id==null || id.trim().isEmpty()){
            //返还错误信息
            return -1;//暂时用-1
        }
        int i=userDao.del(id);
        return i;
    }

    @Override
    public List<ShiTilei> fandAll() {
        List<ShiTilei> user=userDao.fandAll();
        if(user==null || user.size()==0){
            return null;
        }
        return user;
    }

    @Override
    public ShiTilei fand(String id,String pws) {
        if(id==null || id.trim().isEmpty()){
            //异常处理
            return null;
        }
        if(pws==null || pws.trim().isEmpty()){
            //异常处理
            return null;
        }
        ShiTilei user=userDao.fand(id);
        if(user==null){
            //账号错误
            return null;
        }
        if(!pws.equals(user.getPws())){
            //密码错误
            return null;
        }
        return user;
    }

    @Override
    public List<ShiTilei> sort(String name) {
        if(name==null || name.trim().isEmpty()){
            return null;
        }
        List<ShiTilei> user=userDao.sort(name);
        if(user==null || user.size()==0){
            return null;
        }
        return user;
    }
}
