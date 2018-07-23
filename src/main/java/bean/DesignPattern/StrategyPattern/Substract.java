package bean.DesignPattern.StrategyPattern;

public class Substract implements Strategy{
    @Override
    public int doOperation(int numA, int numB) {
        return numA - numB;
    }
}
