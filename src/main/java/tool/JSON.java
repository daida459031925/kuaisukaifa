package tool;

import bean.User.ShiTilei;
import bean.exception.qunbuException;

public class JSON {//目前对user实体类错误处理


    private String code;
    private Object data;
    private String message;

    public static final String ERROR = "-100";
    public static final String PASS = "200";

    public JSON(String s, Object a) {
        this.code=s;
        this.data=a;
    }

    public JSON(Exception e) {
        this.code=ERROR;
        this.message=e.getMessage();
    }

    public JSON(String s, qunbuException e) {
        this.code=s;
        this.message=e.getMessage();
    }

    public JSON(Object object){
        this.data=object;
        this.code=PASS;
    }


    public JSON(){

    }

    public void JSON(String code,Object data,Throwable message){
        this.code=code;
        this.data=data;
        this.message=message.getMessage();
    }

    public void JSON(String code, ShiTilei data, Throwable  message){
        this.code=code;
        this.data=data;
        this.message=message.getMessage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
