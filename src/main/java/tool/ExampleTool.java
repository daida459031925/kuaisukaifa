package tool;

import bean.Entity.WeUserEntity;
import com.google.common.collect.Maps;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;

public class ExampleTool {

    private static HashMap<String, Class> hashMap = Maps.newHashMap();

    /**
     * 将包进行固定化实体类不管是哪个的，在此项目中使用java反射的时候只能使用这个包
     */
    private static final String entity= "bean.Entity.";
    private ExampleTool(){}//让构造函数无法实例化

    /**
     * 这个地方需要添加一个字符串  字符串内容为需要的实体类的字符串
     * @param EntityClass
     * @return
     */
    public static Example getExample(String EntityClass) throws ClassNotFoundException {
        Class mapexample=hashMap.get(EntityClass);
        Class klass=null;
        if(mapexample==null){
            klass=Class.forName(entity+EntityClass);
            hashMap.put(EntityClass,klass);
        }else{
            klass = hashMap.get(EntityClass);
        }
        return new Example(klass);
    }


    public static void main(String[] args) throws ClassNotFoundException {
        /**
         * 这个位置需要获取的内容为全名
         */
        Class klass=Class.forName("bean.Entity.WeUserEntity");
        Example example=new Example(klass);
        System.out.println(klass);
    }

//    public BootgridPageInfoSet fenye(int current,int rowCount,String sort,String nane,String ph ){
//        PageHelper.startPage(current,rowCount);//分页
//        Example example = new Example(CcompareccicModel.class);
//        String by=Jsonutil.getsortby(sort);//解析字段
//        example.setOrderByClause(by);   //排序那个字段
//        Example.Criteria criteria = example.createCriteria();
//        if (StringUtil.isNotEmpty(nane)) {
//            criteria.andLike("xm", "%" + nane + "%");
//        }
//        if (StringUtil.isNotEmpty(ph)) {
//            criteria.andLike("rybh", "%" + ph + "%");
//        }
//        // criteria.andEqualTo("xm", "崔颖");//条件相等
//        // criteria.andGreaterThan("xb", "1");//大于
//        // criteria.andLessThan("xb", "2");//小于
//        // criteria.andIsNotNull("xm");//is not null
//        // criteria.andCondition("xzdqh=","110104");//加各种条件都可以 = like <,可以代替全部的
//        // List<String> values=new ArrayList<String>();
//        // values.add("110104");
//        // values.add("440304");
//        // criteria.andIn("xzdqh", values);//in()
//        // criteria.andBetween("csrq", "1956/01/08", "1966/10/21");//时间相隔
//        // Example.Criteria criteria2 = example.createCriteria();
//        // criteria2.andCondition("xzdqh=","220104");
//        // example.or().andCondition("xzdqh=","220104");//or
//        // example.or(criteria2);//or
//        List<CcompareccicModel> list=service.selectByExample(example);
//        new BootgridPageInfoSet<CcompareccicModel>(list);
//        return new BootgridPageInfoSet<CcompareccicModel>(list);
//    }
}
