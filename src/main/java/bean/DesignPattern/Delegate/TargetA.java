package bean.DesignPattern.Delegate;

public class TargetA implements ITarget{
    @Override
    public void doing(String string) {
        System.out.println("工作者A" + string);
    }
}
