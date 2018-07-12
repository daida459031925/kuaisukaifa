package bean.DesignPattern.PrototypeModel;

public class TemplateTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        //模拟创建一个对象
        Template template=new Template();
        template.AddList("aaa");
        template.setTitle("test1");


        //测试深度复制    引用对象需要在对应的位置重写list的clone
        Template template_clone= (Template) template.clone();
        template_clone.AddList("bbb");
        template_clone.setTitle("test2");

        System.out.println(template);
        System.out.println(template_clone);

        /**
         * 测试A==B?
         * 对任何的对象x，都有x.clone() !=x，即克隆对象与原对象不是同一个对象
         */
        System.out.print("template==template_clone?");
        System.out.println( template == template_clone);

        /**
         * 对任何的对象x，都有x.clone().getClass()==x.getClass()，即克隆对象与原对象的类型一样。
         */
        System.out.print("template.getClass()==template_clone.getClass()?");
        System.out.println(template.getClass() == template_clone.getClass());
    }
}
