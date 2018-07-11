package bean.BuilderPatternMode.Combiner;

import bean.BuilderPatternMode.BankCard.ICBCBank;
import bean.BuilderPatternMode.UnionPay;

import java.util.HashMap;
import java.util.Map;

public class ICBCDebitCard{
    private Map<Object,Object> map = new HashMap<Object,Object>();

    public void put(Object obj,Object obj1){
        map.put(obj,obj1);
    }

    public void show(){
        for(Map.Entry<Object, Object> map :map.entrySet()){
            System.out.println(map.getKey());
            System.out.println(map.getValue());
        }
    }
}
