package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  使用注解标注过滤器
 * @WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器
 * 属性filterName声明过滤器的名称,可选
 * 属性urlPatterns指定要过滤 的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 */
@WebFilter(filterName = "Filter",urlPatterns = "/User/*")
public class Filter implements javax.servlet.Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("-------------------------------------过滤器初始化---------------------------------------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("------------------------------------------------调用前-----------------------------------------");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res=(HttpServletResponse)servletResponse;
        FilterConstant.utf8(req,res);//设置编码为utf8
        filterChain.doFilter(req,res);
        System.out.println("---------------------------------------------调用后------------------------------------------");
    }

    @Override
    public void destroy() {
        System.out.println("---------------------------------------销毁---------------------------------------");
    }
}
