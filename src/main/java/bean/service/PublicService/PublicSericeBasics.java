package bean.service.PublicService;

public interface PublicSericeBasics<T>{

    /**
     * 对单个对象的添加传入的类型为T类型也就是java中的任意类型
     * @param t
     * @return
     */
    public int add(T t) throws ClassNotFoundException;
    public int del(T t);
    public int up(T t);
}
