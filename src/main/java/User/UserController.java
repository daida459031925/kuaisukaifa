package User;

import exception.qunbuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tool.JSON;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserSerice userSerice;

    @RequestMapping("/reception/login.do")
    public String login(String id,String pws){
        userSerice.fand(id,pws);
        return "";//如果登录成功返还登录界面
    }

    @RequestMapping("/reception/loginOut.do")
    public String loginOut(String id,String pws){
        //登出
        return "";//如果登录成功返还登录界面
    }

    @RequestMapping("/reception/addUser.do")
    public String addUser(ShiTilei shitilei){
        userSerice.add(shitilei);
        return "";//如果登录成功返还登录界面
    }

    @RequestMapping("/reception/delUser.do")
    public String delUser(String id){
        userSerice.del(id);
        return "";//如果登录成功返还登录界面
    }

    @RequestMapping("/reception/fandAllUser.do")
    public String fandAllUser(){
        userSerice.fandAll();
        return "";//如果登录成功返还登录界面
    }

    @RequestMapping("/reception/sortUser.do")
    public String sortUser(String name){
        userSerice.sort(name);
        return "";//如果登录成功返还登录界面
    }

    @ExceptionHandler( Exception.class)
    @ResponseBody
    public JSON handleUserNotFound(
            qunbuException e){
        e.printStackTrace();
        return new JSON("-500",e);
    }

}
