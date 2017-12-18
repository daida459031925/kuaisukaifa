package bean.User;


import bean.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tool.MD5;
import tool.dianhuayanzheng;
import tool.mimafuzhadu;

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
            throw new idException("账号不能为空");
        }
        if(shitilei.getName()==null || shitilei.getName().trim().isEmpty()){
            throw new nameException("姓名不能为空");
        }
        if(shitilei.getDianhua()==null || shitilei.getDianhua().trim().isEmpty()){
            throw new dianhuaException("电话不能为空");
        }else{
            if(shitilei.getDianhua().length()==11 && !"0".equals(shitilei.getDianhua().substring(0,1))){
                if(dianhuayanzheng.isMobile(shitilei.getDianhua())){
                }else{
                    throw new dianhuaException("手机格式不对");
                }
            }else{
                if(dianhuayanzheng.isPhone(shitilei.getDianhua())){
                }else{
                    throw new dianhuaException("固话格式不对");
                }
            }
        }
        if(shitilei.getXingbei()==null || shitilei.getXingbei().trim().isEmpty()){
            throw new xingbeiException("性别不能为空");
        }
        if(shitilei.getShenfenzheng()==null || shitilei.getShenfenzheng().trim().isEmpty()){
            throw new shenfenzhengException("身份证不能为空");
            //暂时为任意填写
        }
        if(shitilei.getPws()==null || shitilei.getPws().trim().isEmpty()){
            throw new pwsException("密码不能为空");
        }
        if(mimafuzhadu.validPwd(shitilei.getPws())){
            throw new pwsException("密码复杂度不够");
        }else{
            //进行MD5加密
            shitilei.setPws(MD5.encode(shitilei.getPws()));;
        }
        if(shitilei.getDate()==null){
         shitilei.setDate(new Timestamp(new Date().getTime()));//如果未传入时间则以服务器时间为准
        }
        //在此可以添加验证逻辑
        int i=userDao.add(shitilei);
        return i;
    }

    @Override
    public int del(String id) {
        if(id==null || id.trim().isEmpty()){
            //返还错误信息
            throw new idException("没有账号无法删除");
        }
        int i=userDao.del(id);
        return i;
    }

    @Override
    public List<ShiTilei> fandAll() {
        List<ShiTilei> user=userDao.fandAll();
        return user;
    }

    @Override
    public ShiTilei fand(String id,String pws) {
        if(id==null || id.trim().isEmpty()){
            //异常处理
            throw new idException("账号为空");
        }
        if(pws==null || pws.trim().isEmpty()){
            //异常处理
            throw new pwsException("密码为空");
        }
        ShiTilei user=userDao.fand(id);
        if(user==null){
            //账号错误
            throw new idException("账号错误");
        }
        if(!pws.equals(user.getPws())){
            //密码错误
            throw new pwsException("密码错误");
        }
        return user;
    }

    @Override
    public List<ShiTilei> sort(String name) {
        if(name==null || name.trim().isEmpty()){
            throw new nameException("参数为空");
        }
        List<ShiTilei> user=userDao.sort(name);
        return user;
    }
}
