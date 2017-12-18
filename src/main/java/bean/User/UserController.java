package bean.User;

import bean.exception.qunbuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tool.JSON;

import java.util.List;

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

    @RequestMapping(value = "/reception/addUser.do",method = RequestMethod.POST)//method = RequestMethod.POST  请求一共有八种
    public JSON addUser(@RequestBody ShiTilei shitilei){
        Integer user=userSerice.add(shitilei);
        return new JSON(user);//如果登录成功返还登录界面
    }

    @RequestMapping("/reception/delUser.do")
    public JSON delUser(String id){
        //userSerice.del(id);  //删除用户暂时不做任何操作
        return new JSON();//删除该用户
    }

    @RequestMapping(value = "/reception/fandAllUser.do",method = RequestMethod.GET)
    public JSON fandAllUser(Shi){
        List<ShiTilei> user=userSerice.fandAll();
        return new JSON(user);//返还所有用户信息
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
