package bean.DesignPattern.FactoryModel.FactoryModel;

/**
 * 创建公共方法 例如需要制作一个鼠标    但是制作鼠标的工厂有很多
 * 那么面向接口编程，就创建一个 鼠标制作方法
 *
 * 所有子类来实现这个接口来从写 创造鼠标的方法
 */
public interface Mouse {
    public void create();
}
