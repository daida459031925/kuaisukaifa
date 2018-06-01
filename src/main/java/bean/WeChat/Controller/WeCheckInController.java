package bean.WeChat.Controller;

import bean.PublicLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/we/CheckIn")
public class WeCheckInController implements PublicLog{

    @RequestMapping(value = "/addUserCheckIn.do",method = RequestMethod.POST)
    @ResponseBody
    public void addUserCheckIn(HttpServletRequest req, HttpServletResponse res){

    }
}
