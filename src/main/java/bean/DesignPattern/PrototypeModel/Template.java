package bean.DesignPattern.PrototypeModel;

import java.util.ArrayList;

/**
 * 原型模式就是普通的类   或者说配合工厂模式产生的 。  基本使用就是 实现Cloneable
 * 重写 clone（）方法来实现复制 。
 * 复制多个对象给用户可以保证  原始数据不变   那就用浅复制  浅复制的基本类型在复制出来的对象
 * 是不会被影响的，也就是说修改复制出来的对象不会对原始对象修改。
 * 引用类型的就不一样   这个必须实现深度复制  才能实现和基本类型一样的效果。否则的话就修改复制的对象也会影响原始数据
 */
public class Template implements Cloneable{

    private String title;

    private ArrayList<String> list = new ArrayList<String>();

    {
        System.out.println("没有static的{...}方法");
    }

    static {
        System.out.println("有static的{...}方法");
    }

    public Template(){
        System.out.println("构造方法执行");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void AddList(String list) {
        this.list.add(list);
    }

    @Override
    public String toString() {
        return "Template{" +
                "title='" + title + '\'' +
                ", list=" + list +
                '}';
    }

    @Override
    protected Template clone() throws CloneNotSupportedException {
        //返回的是浅复制  复制出来的对象与原来的对象一致 但是对象中引用对象单独的复制无法复制出来 浅复制复制对象的时候只复制了地址值
        Template template=(Template) super.clone();
        //以下代码属于深度复制  这样可以直接复制引用对象。 深度复制需要自己手动填写，需要复制哪些引用对象的时候就进行重写获取.clone();
        this.list=(ArrayList<String>) this.list.clone();
        return template;
    }
}
