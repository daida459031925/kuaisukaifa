package bean.tet;

import bean.Rabbit.HelloSender;
import bean.exception.AbstractController;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

//import org.junit.Assert;
//import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tool.PageInfo;

import java.util.List;


@RestController
public class testBeanController /*之前的方式extends AbstractController*/{

    @Autowired
    private TestBeanesrviceimpl testBeanesrviceimpl;

    @Autowired
    private HelloSender helloSender;

    @RequestMapping("/test.do")//测试demo是连接本地数据库
//    @ResponseBody
    public  String test(){
        for(int i=0;i<1;i++){
            helloSender.send(i+"");
        }
//        System.out.println("**************************************************************");
//        List<testBean> m=testBeanesrviceimpl.findAll();
//        System.out.println(m);

        return "";
    }



    @RequestMapping("/test1.do")
//    @ResponseBody
    public void testFindByPage() {
        //page是gethub的
        Page<testBean> persons = testBeanesrviceimpl.findByPage(1, 1);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<testBean> pageInfo = new PageInfo<>(persons);
        System.out.println(pageInfo.toString()+"***************************************");
        System.out.println(JSON.toJSONString(pageInfo)+"***************************************");
//        Assert.assertNotNull(persons);
//        logger.debug(pageInfo.toString());
//        logger.debug(JSON.toJSONString(pageInfo));
    //    System.out.println(pageInfo.getList());
//        return persons;
    }
}
