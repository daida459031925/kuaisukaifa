package bean.BuilderPatternMode.BankCard;

import bean.BuilderPatternMode.UnionPay;

public abstract class ICBCBank implements UnionPay {

    @Override
    public String getPayName() {
        return "ICBC";
    }
}
