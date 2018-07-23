package bean.DesignPattern.Delegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leader {

    private Map<String,ITarget> iTargets = new HashMap<String,ITarget>();

    //这里根据策略委派任务
    public Leader(){
        iTargets.put("1",new TargetA());
        iTargets.put("2",new TargetB());
    }

    public void doing(String string){
        iTargets.get(string).doing(string);
    }

    public static void main(String[] args) {
        //客户请求（Boss）、委派者（Leader）、被被委派者（Target）
        //委派者要持有被委派者的引用
        //代理模式注重的是过程， 委派模式注重的是结果
        //策略模式注重是可扩展（外部扩展），委派模式注重内部的灵活和复用
        //委派的核心：就是分发、调度、派遣
        //委派模式：就是静态代理和策略模式一种特殊的组合
        new Leader().doing("2");
    }
}
