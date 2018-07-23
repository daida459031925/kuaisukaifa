package bean.DesignPattern.StrategyPattern;

public class Add implements Strategy{

    @Override
    public int doOperation(int numA, int numB) {
        return numA + numB;
    }
}
