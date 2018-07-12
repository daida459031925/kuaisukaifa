package bean.DesignPattern.BuilderPatternMode.BankCard;

import bean.DesignPattern.BuilderPatternMode.UnionPay;

public abstract class ICBCBank implements UnionPay {

    @Override
    public String getPayName() {
        return "ICBC";
    }
}
