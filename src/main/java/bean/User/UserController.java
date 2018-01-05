package bean.User;

import bean.exception.AbstractController;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tool.JSON;


@Controller
@RequestMapping("/User")
public class UserController /*之前的方式extends AbstractController*/{

    @Autowired
    private UserSerice userSerice;

    @RequestMapping("/reception/login.do")
    public String login(String id, String pws){
        //userSerice.fand(id,pws);
        //System.out.println("********************************");
//        return "hello";
        return "redirect:/guanli/helo.html";//重定向可以直接从定向；但是需要访问链接对，实在没把握就用绝对路径
    }

    @RequestMapping("/reception/loginOut.do")
    public String loginOut(String id,String pws){
        //登出
        return "hello";//如果登录成功返还登录界面
    }

    /**
     *
     * @param shitilei
     * @return
     * 添加的是实体类
     * String id;//用户id  唯一标识符
     * String name;//用户名字
     * Timestamp Date;//创建时间
     * String dianhua;//电话
     * String xingbei;//性别
     * String shenfenzheng;//身份证
     * String pws;//密码
     * 传输格式是JSON
     */
    @RequestMapping(value = "/reception/addUser.do",method = RequestMethod.POST)//method = RequestMethod.POST  请求一共有八种
    public JSON addUser(ShiTilei shitilei) {
        Integer user=userSerice.add(shitilei);
        return new JSON(user);
    }

    @RequestMapping("/reception/delUser.do")
    public JSON delUser(String id){
        int i=userSerice.del(id);  //删除用户暂时不做任何操作
        return new JSON(i);//删除该用户
    }

    @RequestMapping(value = "/reception/fandAllUser.do",method = RequestMethod.GET)
    @ResponseBody
    public JSON fandAllUser(/*通过@RequestParam注解指定具体的参数名称*/
                            @RequestParam(value = "pageNO" ) int pageNO, @RequestParam(value = "pageSize")int pageSize){
        /**
         * page需要导入的都是是gethub的包
         * 返还对象必须是Page<T>这种类型  这样框架才会给你分页信息
         *
         */
        Page<ShiTilei> user=userSerice.fandAll(pageNO,pageSize);
        /**
         * 将page对象放入pageInfo  进行序列化后然后有分页信息
         * 分页对象分页完毕后，不管是habeliete，还是mybites
         * 都是一样会出现一个问题就是当前端请求页数大于分页内容中最大值时
         * 不管以后如何添加分页的pageNO，都会返还最后一页的数据
         * */
        PageInfo<ShiTilei> pageInfo = new PageInfo<>(user);
        System.out.println(pageInfo.getPageSize());
        return new JSON(pageInfo);//返还所有用户信息
    }

    @RequestMapping("/reception/sortUser.do")
    public String sortUser(String name){
        userSerice.sort(name);
        return "";//如果登录成功返还登录界面
    }



}
