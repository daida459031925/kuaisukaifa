package bean.DesignPattern.FactoryModel.AbstractFactoryModel;

public class DellFactory extends PcFactory {
    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }

    @Override
    public Keybo createKeybo() {
        return new DellKeybo();
    }
}
