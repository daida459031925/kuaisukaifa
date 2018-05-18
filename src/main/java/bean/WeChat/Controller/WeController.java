package bean.WeChat.Controller;

import bean.WeChat.StringConstant.Constant;
import config.redis.tool.Redis_tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tool.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/we")
/**
 * 这两个session；getsession目前是为了测试redis+session 共享实现服务器统一session的管理的操作
 * 2017.5.17日已经实现session共享
 */
public class WeController{

    private static Logger LOGGER = LoggerFactory.getLogger(WeController.class);

    @RequestMapping(value = "/reception/session.do",method = RequestMethod.GET)
    @ResponseBody
    public JSON getdata(HttpServletRequest req, HttpServletResponse res){
       String string=req.getParameter("js_code");
       if(string==null || string.trim().isEmpty()){
            throw new RuntimeException();
       }
       Map map= Constant.getOpenid_Session_key(string);
       //在这里需要进行map返回值的判断 若返回的值为某某某错误码的时候需要拦截。让用户重新登陆
       LOGGER.info(map.toString());
       String uuid= Constant.getUUID();
       LOGGER.info(uuid);
       req.getSession().setAttribute(uuid,map.toString());
       LOGGER.info((req.getSession().getAttribute(uuid)).toString());
       return new JSON(uuid);
    }

    @RequestMapping(value = "/reception/getsession.do",method = RequestMethod.GET)
    @ResponseBody
    public JSON getsession(HttpServletRequest req, HttpServletResponse res){
        String string=req.getParameter("session");
        if(string==null || string.trim().isEmpty()){
            throw new RuntimeException();
        }
        LOGGER.info((req.getSession().getAttribute(string)).toString());
        return null;
    }
}
