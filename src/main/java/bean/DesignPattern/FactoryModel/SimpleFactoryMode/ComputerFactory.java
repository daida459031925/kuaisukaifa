package bean.DesignPattern.FactoryModel.SimpleFactoryMode;

/**
 * 简单工厂模式并非23种中的一种，主要作用就是将相似或者一样的组成一个方法通过传入固定参数来实现new对象
 * 的管理，这样方便整体代码的管理 修改返回对象的时候直接修改工厂类中的对象。不用再去代码中查找这个对象哪里用到了再去修改。
 */
public class ComputerFactory {
    public static Computer createFactory(int i){

        switch (i){
            case 0:
                return new dellComputer();
            case 1:
                return new HPComputer();
            default:
                return null;
        }

    }
}
