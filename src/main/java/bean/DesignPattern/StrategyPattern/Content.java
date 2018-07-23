package bean.DesignPattern.StrategyPattern;

public class Content {
    private Strategy strategy;

    public Content(Strategy strategy){
        this.strategy=strategy;
    }

    public int executeStrategy(int numA,int numB){
        return strategy.doOperation(numA,numB);
    }

    public static void main(String[] args) {
        Content context = new Content(new Add());
        System.out.println(context.executeStrategy(1,2));
    }
}
