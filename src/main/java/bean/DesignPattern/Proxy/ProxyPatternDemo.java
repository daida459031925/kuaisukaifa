package bean.DesignPattern.Proxy;

public class ProxyPatternDemo {
    public static void main(String[] args) {
        for(int i =0;i<20;i++){
            Image image =new ProxyImage("test"+i+".png");
            //图片重磁盘加载
            image.Display();
            System.out.println("****");
            //图片将无法从磁盘加载
            image.Display();
        }
    }
}
