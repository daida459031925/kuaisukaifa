package bean.BuilderPatternMode;

import java.math.BigDecimal;

/**
 * 建造者模式，通用接口，将所有银行卡都有的共有属性放入然后用各个银行的单独抽象类来实现这个方法。若不给具体实现则需要由对应的 继承者来实现
 */
public interface UnionPay {
    public String getPayName();
//    public String UnionPayCardNumber();
//    public BigDecimal UnionPayBigDecimal();//不涉及到具体业务已经金钱
}
