package RestBaseController;

//@Slf4j

import BasicDao.BasicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Slf4j  这个是日志   目前我采用的是自己写log
 */
public class BaseController<Biz extends BasicDAO,Entity> {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected Biz basicDAO;

    @RequestMapping(value = "/all"/*,method = RequestMethod.GET*/)//固定只能使用get获取
    public List<Entity> getUserList(){
        System.out.println();
        return basicDAO.selectAll();
    }

    @RequestMapping(value = "/ins"/*,method = RequestMethod.GET*/)//固定只能使用get获取
    public int inss(Entity entity){
        return basicDAO.ins(entity);
    }
}
