package BasicDao;

import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;


/**
 * 此类的目的是为了集成增删查改等简单操作简化java代码的复杂度。
 * 基础公共的父类实现基础的增删查改，类似于hibernate的包一样直接写出基础父类实现统一接口，
 * 需要传入什么对象就传入什么对象，2018.4.23 目前只写一个selectALL
 * @param <M>
 * @param <T>
 */

public abstract class BasicDAO <M extends Mapper<T>,T>{
    @Autowired
    protected M mapper;

    public BasicDAO(){
        System.out.println();
    }

    public List<T> selectAll(){
        return this.mapper.selectAll();
    }

    public int ins(T t){
        return this.mapper.insertSelective(t);
    }

}
