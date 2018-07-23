package bean.DesignPattern.Proxy;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 这个类主要是来创造所需要的类的实现类
 *
 *  代理模式中也使用到了单例模式
 *
 *  */
public class ProxyImage implements Image{

    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName){
        this.fileName=fileName;
    }


    @Override
    public void Display() {
        if(realImage == null){
            realImage = new RealImage(fileName);
        }
        realImage.Display();
    }
}
