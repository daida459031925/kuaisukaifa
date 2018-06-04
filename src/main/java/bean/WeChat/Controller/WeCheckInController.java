package bean.WeChat.Controller;

import bean.Entity.WeCheckInEntity;
import bean.PublicLog;
import bean.service.WeService.WeGeographicalLocationService;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tool.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/we/CheckIn")
public class WeCheckInController implements PublicLog{

    @Autowired
    private WeGeographicalLocationService weGeographicalLocationService;

    @RequestMapping(value = "/addUserCheckIn.do",method = RequestMethod.POST)
    @ResponseBody
    public JSON addUserCheckIn(HttpServletRequest req, HttpServletResponse res) throws ClassNotFoundException {
        String name=req.getParameter("name");//什么街道神什么的
        String latitude=req.getParameter("latitude");//纬度
        String longitude=req.getParameter("longitude");//精度
        String desc=req.getParameter("desc");//详细信息
        String session_3rd=req.getParameter("session_3rd");

        boolean name_bol = (name == null || name.trim().isEmpty());
        boolean latitude_bol = (latitude == null || latitude.trim().isEmpty());
        boolean longitude_bol = (longitude == null || longitude.trim().isEmpty());
        boolean desc_bol = (desc == null || desc.trim().isEmpty());
        boolean session_3rd_bol = (session_3rd == null || session_3rd.trim().isEmpty());

        if(latitude_bol || longitude_bol || name_bol || desc_bol || session_3rd_bol){
            return new JSON("errcode需要一个完整的常量来进行数据的保证目前使用这个字符串算了");
        }

        WeCheckInEntity weCheckInEntity = new WeCheckInEntity();
        weCheckInEntity.setOpenid(session_3rd);
        weCheckInEntity.setLatitude(latitude);
        weCheckInEntity.setLongitude(longitude);
        weCheckInEntity.setDetailed_information(desc);
        weCheckInEntity.setStreet_number(name);
        int count=weGeographicalLocationService.add(weCheckInEntity);

        return new JSON(count);
    }

    @RequestMapping(value = "/getUserCheckInAll.do",method = RequestMethod.GET)
    @ResponseBody
    public JSON selectAll(HttpServletRequest req, HttpServletResponse res) throws ClassNotFoundException {
        String session_3rd=req.getParameter("session_3rd");
        boolean session_3rd_bol = (session_3rd == null || session_3rd.trim().isEmpty());
        if(session_3rd_bol){
            return new JSON("errcode需要一个完整的常量来进行数据的保证目前使用这个字符串算了");
        }
        List<WeCheckInEntity> weCheckInEntitys = new ArrayList<WeCheckInEntity>();
        WeCheckInEntity weCheckInEntity =new WeCheckInEntity();
        weCheckInEntity.setOpenid(session_3rd);
        weCheckInEntitys=weGeographicalLocationService.selectAllKEY(weCheckInEntity);
        return new JSON(weCheckInEntitys);
    }
}
