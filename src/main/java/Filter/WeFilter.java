package Filter;

import bean.service.PublicService.RedisSerice;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "WeFilter",urlPatterns = "/we/*")
public class WeFilter extends RedisSerice implements javax.servlet.Filter{
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


        String path=req.getServletPath();
        if(path.indexOf("/we/reception") >= 0){
            System.out.println(req.getServletPath());
            filterChain.doFilter(req,res);
        }else{
            String key=req.getParameter("session_3rd");
            if(key !=null && !key.trim().isEmpty() && redis_tool.exists(key)){
                System.err.println("//如果存在的话那么直接那么刷新redis时间***********************************");
                redis_tool.surpass(key,timeOut);
                filterChain.doFilter(req,res);
            }else{
                //如果不存在的话   这里就无法获得小程序的code 以及session的内容
                //   所以这个地方就直接让小程序重新登陆
            }
        }
        System.out.println("we---------------------------------------------调用后------------------------------------------");
    }

    @Override
    public void destroy() {
        System.out.println("we---------------------------------------销毁---------------------------------------");
    }
}
