package tool;

import com.alibaba.druid.util.StringUtils;

public class mimafuzhadu {
    /**
     * 校验密码
     * 1、长度不小于6位
     * 2、必须以字母开头
     * 3、必须包含特殊字符
     * 4、必须包含数字
     * @param pwd
     * @return
     */
    public static boolean validPwd(String pwd){
        if(StringUtils.isEmpty(pwd)){
            return false;
        }
        if(pwd.length() > 6 && pwd.length() <16){
            return false;
        }
        if(pwd.matches("^[a-zA-z](.*)") && pwd.matches("(.*)[-`=\\\\\\[\\];',./~!@#$%^&*()_+|{}:\"<>?]+(.*)") && pwd.matches("(.*)\\d+(.*)")){
            return true;
        }
        return false;
    }
}
