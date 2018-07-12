package bean.DesignPattern.FactoryModel.SimpleFactoryMode;

public class dellComputer implements Computer {
    @Override
    public void createComputer() {
        Computer computer=ComputerFactory.createFactory(0);
    }
}
