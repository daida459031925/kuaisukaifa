package bean.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import tool.JSON;

public abstract class AbstractController {
    /**
     * 其他Controller在继承这个类后    将所用异常添加进来 只需要继承 防止代码重复啰嗦
     * 在其他控制器方法执行出现异常时候, 执行
     * 异常处理方法 handleException
     */
    @ExceptionHandler( Exception.class)
    @ResponseBody
    public Object handleException(
            Exception e){
        e.printStackTrace();
        return new JSON(e);
    }


}
