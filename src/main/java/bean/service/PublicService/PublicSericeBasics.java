package bean.service.PublicService;

import java.util.List;

public interface PublicSericeBasics<T>{

    /**
     * 对单个对象的添加传入的类型为T类型也就是java中的任意类型
     * @param t
     * @return
     */
    public int add(T t) throws ClassNotFoundException;
    public int del(T t);
    public int up(T t);
    public T selectKEY(T t,String ...strings) throws ClassNotFoundException;
    public List<T> selectAllKEY(T t, String ...strings) throws ClassNotFoundException;
}
