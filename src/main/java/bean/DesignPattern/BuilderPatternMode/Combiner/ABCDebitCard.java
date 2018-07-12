package bean.DesignPattern.BuilderPatternMode.Combiner;

import java.util.HashMap;
import java.util.Map;

/**
 * 这个类主要是进行装配，也就是说具体的业务实现类，具体需要什么方法根据当前类型的银行卡创建
 */
public class ABCDebitCard{
    private Map<Object,Object> map = new HashMap<Object,Object>();

    public ABCDebitCard(){
        map.put("Combiner","ABCDebitCard");
    }

    public void put(Object obj,Object obj1){
        map.put(obj,obj1);
    }

    public void show(){
        for(Map.Entry<Object, Object> map :map.entrySet()){
            System.out.println(map);
        }
    }
}
