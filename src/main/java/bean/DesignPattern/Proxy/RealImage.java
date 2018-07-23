package bean.DesignPattern.Proxy;

public class RealImage implements Image{
    private String fileName;

    public RealImage(String fileName){
        this.fileName=fileName;
        this.loadFromDisk(fileName);
    }

    @Override
    public void Display() {
        System.out.println("RealImage " + fileName);
    }

    /**
     * 这里面或者说这个类中，添加多个不同的私有方法
     * 这样子就是对这个类的实体操作或者其他业务。
     * @param fileName
     */
    private void loadFromDisk(String fileName){
        System.out.println("Loading " + fileName);
    }
}
