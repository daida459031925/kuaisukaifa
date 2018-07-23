package bean.DesignPattern.Delegate;

public class TargetB implements ITarget{
    @Override
    public void doing(String string) {
        System.out.println("工作者B"+string);
    }
}
