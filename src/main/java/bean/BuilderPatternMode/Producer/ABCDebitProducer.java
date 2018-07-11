package bean.BuilderPatternMode.Producer;

import bean.BuilderPatternMode.BankCard.ABCBank;
import bean.BuilderPatternMode.Combiner.ABCDebitCard;

/**
 * 继承银行总类的生产者，主要功能进行调用方法进行生产，也就是当组成器无法满足某些功能的时候，那么这个类基本不动，需要修改的是对应的组成器。
 * 其扩展功能使用接口
 */
public class ABCDebitProducer extends ABCBank{
    private ABCDebitCard abcDebitCard = new ABCDebitCard();

    public ABCDebitProducer(){
        abcDebitCard.put("PayName",super.getPayName());
        abcDebitCard.put("abc","abc");
        abcDebitCard.show();
    }

    public static void main(String[] args) {
        new ABCDebitProducer();
    }
}
