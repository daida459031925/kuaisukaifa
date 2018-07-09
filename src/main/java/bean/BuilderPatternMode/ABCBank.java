package bean.BuilderPatternMode;

public abstract class ABCBank implements UnionPay{
    @Override
    public String UnionPayName() {
        return "ABC";
    }
}
