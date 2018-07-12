package bean.DesignPattern.FactoryModel.SimpleFactoryMode;

public class HPComputer implements Computer{
    @Override
    public void createComputer() {
        Computer computer=ComputerFactory.createFactory(1);
    }
}
