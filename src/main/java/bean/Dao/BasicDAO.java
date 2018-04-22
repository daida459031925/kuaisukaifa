package bean.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * 此类的目的是为了集成增删查改等简单操作简化java代码的复杂度。
 * @param <M>
 * @param <T>
 */
public abstract class BasicDAO <M extends Mapper<T>,T>{
    @Autowired
    protected M mapper;

    public List<T> selectAll(){
        return this.mapper.selectAll();
    }

}
