package bean.DesignPattern.FactoryModel.AbstractFactoryModel;

public class HpFactory extends PcFactory{
    @Override
    public Mouse createMouse() {
        return new HpMouse();
    }

    @Override
    public Keybo createKeybo() {
        return new HpKeybo();
    }
}
