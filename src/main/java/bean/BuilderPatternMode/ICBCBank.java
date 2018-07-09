package bean.BuilderPatternMode;

public abstract class ICBCBank implements UnionPay{
    @Override
    public String UnionPayName() {
        return "ICBC";
    }
}
