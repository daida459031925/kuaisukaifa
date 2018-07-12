package bean.DesignPattern.FactoryModel.AbstractFactoryModel;

public class Test {
    public static void main(String[] args) {
        PcFactory pcFactory= new HpFactory();
        Keybo keybo=pcFactory.createKeybo();
        Mouse mouse=pcFactory.createMouse();
        keybo.sayHi();
        mouse.sayHi();

    }
}
