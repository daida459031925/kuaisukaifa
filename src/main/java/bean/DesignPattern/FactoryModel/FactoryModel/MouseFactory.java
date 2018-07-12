package bean.DesignPattern.FactoryModel.FactoryModel;

/**
 * 定义的是鼠标工厂    相当于我需要找哪个工厂去制作鼠标
 * 工厂也是有很多。这时候就抽成一个接口 。让所有工厂 都实现这个接口
 * 然后要制作哪个 工厂里的鼠标就创建 对应的   子类
 */
public interface MouseFactory {
    public Mouse createMouse();
}
