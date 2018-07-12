package bean.DesignPattern.FactoryModel.FactoryModel;

public class DellMouseFactory implements MouseFactory{
    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }
}
