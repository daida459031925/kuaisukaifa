package bean.WeChat.Controller;

import bean.Entity.WeUserEntity;
import bean.WeChat.StringConstant.Constant;
import bean.service.WeService.WeUserService;
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
import java.io.IOException;
import java.util.Date;
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

    @Autowired
    private WeUserService weUserService;

    @RequestMapping(value = "/reception/session.do",method = RequestMethod.GET)
    @ResponseBody
    public JSON getdata(HttpServletRequest req, HttpServletResponse res) throws IOException, InterruptedException {
       String string=req.getParameter("js_code");
       String session=req.getParameter("session");
       String nickName=req.getParameter("nickName");
       String gender=req.getParameter("gender");
       String language=req.getParameter("language");
       String city=req.getParameter("city");
       String province=req.getParameter("province");
       String country=req.getParameter("country");
       String avatarUrl=req.getParameter("avatarUrl");
       String phone=req.getParameter("phone");
       boolean boolean_string= (string==null || string.trim().isEmpty());
       boolean boolean_session= (session==null || session.trim().isEmpty());
       if( boolean_string && boolean_session){
           return new JSON("errcode需要一个完整的常量来进行数据的保证目前使用这个字符串算了");//登陆失败参数没有传入让他返回或者重启小程序
       }
       if(!boolean_session){
           Object reqsession=req.getSession().getAttribute(session);
           if(! (reqsession == null) ){
                return new JSON(reqsession.toString());
           }
       }

       if(boolean_string){
           //传入上来没有code 只有session的时候 这样就不能从新发送自己后台的3rd_session所以这时候返还错误码给前段让他获得code然后进行再次登陆
           return new JSON("errcode需要一个完整的常量来进行数据的保证目前使用这个字符串算了");//登陆失败参数没有传入让他返回或者重启小程序
       }
            //如果传过来不为空的情况下则从新去腾讯里的服务器获取数据
       Map map= Constant.getOpenid_Session_key(string);
       //在这里需要进行map返回值的判断 若返回的值为某某某错误码的时候需要拦截。让用户重新登陆
       Object openid= map.get("openid");
       Object session_key= map.get("session_key");
       if(openid==null || openid.toString().trim().isEmpty() && session_key==null || session_key.toString().trim().isEmpty()){
           return new JSON("errcode需要一个完整的常量来进行数据的保证目前使用这个字符串算了");//登陆失败参数没有传入让他返回或者重启小程序
       }
       LOGGER.info(map.toString());
       String uuid= Constant.getUUID();
       LOGGER.info(uuid);
       req.getSession().setAttribute(uuid,map.toString());
       LOGGER.info((req.getSession().getAttribute(uuid)).toString());
       //涉及到需要到数据库内容时候直接传入准备好的对象不做任何认证，认证内容到service去做
       WeUserEntity weUserEntity = new WeUserEntity();
       weUserEntity.setAvatar_url(avatarUrl);
       weUserEntity.setCity(city);
       weUserEntity.setCountry(country);
       weUserEntity.setGender(gender);
       weUserEntity.setLanguage(language);
       weUserEntity.setNick_name(nickName);
       weUserEntity.setOpenid(openid.toString());
       weUserEntity.setPhone(phone);
       weUserEntity.setProvince(province);
       weUserEntity.setAdd_time(new Date());
       weUserService.add(weUserEntity);
       return new JSON(uuid);
    }

    @RequestMapping(value = "/reception/bindingWe.do",method = RequestMethod.GET)
    @ResponseBody
    public JSON getsession(HttpServletRequest req, HttpServletResponse res){

        return null;
    }



}
