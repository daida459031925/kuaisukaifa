package bean.DesignPattern.FactoryModel.AbstractFactoryModel;

/**
 * 抽象工厂模式 主要实现是一个抽象父类将所有需要实现的方法集成在这个父类中
 * 特有属性用接口然后实现在特定类中
 *
 * 抽象类外还需要一个  调用抽象类的 生产器。。。不用生产器的话   影响实际上也不太大
 * 只是后期不好管理   需要向简单工厂模式中的ComputerFactory  类一样  给定指定的名字
 * 来生产 对饮的实体类
 */
public abstract class PcFactory {
    public abstract Mouse createMouse();
    public abstract Keybo createKeybo();
}
