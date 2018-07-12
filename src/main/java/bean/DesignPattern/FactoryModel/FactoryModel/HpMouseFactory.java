package bean.DesignPattern.FactoryModel.FactoryModel;

public class HpMouseFactory implements MouseFactory{
    @Override
    public Mouse createMouse() {
        return new HpMouse();
    }
}
