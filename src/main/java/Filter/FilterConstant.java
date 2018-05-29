package Filter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 可以在过滤器中的req和res添加新的方法，或者自己的逻辑
 * 添加完成后直接在过滤器中进行代码的修改。
 * 由于这个是全局的所以感觉这样会好点
 */
public class FilterConstant {

    public static void utf8(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
//        res.setContentType("text/html;charset=utf-8");
    }
}
