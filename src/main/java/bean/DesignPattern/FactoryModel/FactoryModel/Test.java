package bean.DesignPattern.FactoryModel.FactoryModel;

public class Test {
    public static void main(String[] args) {
        MouseFactory mouseFactory =new DellMouseFactory();
        Mouse mouse=mouseFactory.createMouse();
        mouse.create();
    }
}
