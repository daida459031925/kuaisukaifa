package BasicDao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;



/**
 * 此类的目的是为了集成增删查改等简单操作简化java代码的复杂度。
 * 基础公共的父类实现基础的增删查改，类似于hibernate的包一样直接写出基础父类实现统一接口，
 * 需要传入什么对象就传入什么对象
 * 基础类不能被扫描到
 * @param <T>
 */

public interface BasicDAO<T> extends Mapper<T>,MySqlMapper<T>{

}
