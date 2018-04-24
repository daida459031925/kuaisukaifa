package bean.Mapper;

import bean.Entity.UserEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 写一个接口，这个接口的内容是需要自己查询插入等等一系列的东西
 * 简单来说就是在工具包内容中部满足实际的业务需求的时候来进行额外的实现
 *
 */
public interface UserMapper extends Mapper<UserEntity>{

    public List<UserEntity> getUserMapperList();
}
