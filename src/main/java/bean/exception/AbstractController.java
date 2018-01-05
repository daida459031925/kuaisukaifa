package bean.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import tool.JSON;

@ControllerAdvice//这个注解实现全局异常检查
public /*之前的方式abstract */class AbstractController {
    /**
     * 其他Controller在继承这个类后    将所用异常添加进来 只需要继承 防止代码重复啰嗦
     * 在其他控制器方法执行出现异常时候, 执行
     * 异常处理方法 handleException
     *
     * 这个是之前使用的方法使用抽象类abstract class AbstractController然后所有contoroller类集成抽象类。
     * 然而spring boot 使用注解方式解耦@ControllerAdvice   使用后直接进行全局异常处理
     * 使得类可以放开继承代码优化 从而解耦
     */
    @ExceptionHandler( Exception.class)
    @ResponseBody
    public Object handleException(
            Exception e){
        e.printStackTrace();
        return new JSON(e);
    }


}
