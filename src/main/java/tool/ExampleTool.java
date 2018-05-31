package tool;

import com.google.common.collect.Maps;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;

public class ExampleTool {

    private static HashMap<String, Example> hashMap = Maps.newHashMap();

    /**
     * 将包进行固定化实体类不管是哪个的，在此项目中使用java反射的时候只能使用这个包
     */
    private static final String entity= "bean.Entity.";
    private ExampleTool(){}//让构造函数无法实例化

    /**
     * 这个地方需要添加一个字符串  字符串内容为需要的实体类的字符串
     * @param EntityClass
     * @return
     */
    public static Example getExample(String EntityClass) throws ClassNotFoundException {
        Example mapexample=hashMap.get(EntityClass);
        Class klass=null;
        if(mapexample==null){
            klass=Class.forName(entity+EntityClass);
        }else{
            return hashMap.get(EntityClass);
        }
        if(klass==null){
            return null;
        }
        Example example=new Example(klass);
        hashMap.put(EntityClass,example);
        return example;
    }


    public static void main(String[] args) throws ClassNotFoundException {
        /**
         * 这个位置需要获取的内容为全名
         */
        Class klass=Class.forName("bean.Entity.WeUserEntity");
        Example example=new Example(klass);
        System.out.println(klass);
    }

}
