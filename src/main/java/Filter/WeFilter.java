package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "WeFilter",urlPatterns = "/we/*")
public class WeFilter implements javax.servlet.Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("we-------------------------------------过滤器初始化---------------------------------------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("we------------------------------------------------调用前-----------------------------------------");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res=(HttpServletResponse)servletResponse;
        FilterConstant.utf8(req,res);//设置编码为utf8
        filterChain.doFilter(req,res);
        System.out.println("we---------------------------------------------调用后------------------------------------------");
    }

    @Override
    public void destroy() {
        System.out.println("we---------------------------------------销毁---------------------------------------");
    }
}
