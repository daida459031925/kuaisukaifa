package bean.tet;


import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;


import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

@Mapper
public interface TestBeanDao {

    //以前怎么写xml就把之前写的东西cop进来  但是前后要加script的标签
//    @Select("<script>" +"select * from p_post <if test= \"1 == 1 \"> where id != 1 </if>" + "</script>")
    public List<testBean> findAll();

    @Options(useGeneratedKeys = true, keyProperty = "id")
//     @Insert("insert into sys_user (`username`, `password`, `email`, `mobile`, `status`, `user_id_create`, `gmt_create`, `gmt_modified`, `name`)"
//     + "values (#{username}, #{password}, #{email}, #{mobile}, #{status}, #{userIdCreate}, #{gmtCreate}, #{gmtModified}, #{name})")
    @Insert("INSERT INTO `p_post` ( `title`, `person_id`) VALUES ('今天天气不错','1')")
     int save(/*testBean obj*/);


//
//    @Select("<script>" +"select * from p_post <if test= \"1 == 1 \"> where id != 1 </if>" + "</script>")
//    public Page<testBean> findAll(Pageable pageable);

//    @Select("<script>" +"select * from p_post <if test= \"1 == 1 \"> where id != 1 </if>" + "</script>")
    public Page<testBean> findByPage();

}
