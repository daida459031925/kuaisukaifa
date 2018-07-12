package bean.DesignPattern.BuilderPatternMode.BankCard;

import bean.DesignPattern.BuilderPatternMode.UnionPay;

/**
 * 主要来实现接口中共有属性，也许有些共有属性在当前银行总类不好实现，
 * 则放入到单独银行卡类型中实现
 */
public abstract class ABCBank implements UnionPay {

    @Override
    public String getPayName() {
        return "ABC";
    }
}
