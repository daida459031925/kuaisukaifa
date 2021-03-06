package bean.Entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

//@Entity  这个是jap分页操作数据库的  使用mybist不用这个

@Table(name = "user")
public class UserEntity implements Serializable{

//    INSERT INTO `daida`.`user` (`id`, `name`, `Date`, `dianhua`, `xingbei`, `shenfenzheng`, `pws`) VALUES ('6', '6', '2018-01-03 13:54:06', '6', '6', '6', '6');

    @Id
//    @GeneratedValue(generator = "UUID")
    private String id;//用户id  唯一标识符
    @Column
    private String name;//用户名字
    @Column
    private Timestamp Date;//创建时间
    @Column
    private String dianhua;//电话
    @Column
    private String xingbei;//性别
    @Column
    private String shenfenzheng;//身份证
    @Column
    private String pws;//密码

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", Date=" + Date +
                ", dianhua='" + dianhua + '\'' +
                ", xingbei='" + xingbei + '\'' +
                ", shenfenzheng='" + shenfenzheng + '\'' +
                ", pws='" + pws + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDate() {
        return Date;
    }

    public void setDate(Timestamp date) {
        Date = date;
    }

    public String getDianhua() {
        return dianhua;
    }

    public void setDianhua(String dianhua) {
        this.dianhua = dianhua;
    }

    public String getXingbei() {
        return xingbei;
    }

    public void setXingbei(String xingbei) {
        this.xingbei = xingbei;
    }

    public String getShenfenzheng() {
        return shenfenzheng;
    }

    public void setShenfenzheng(String shenfenzheng) {
        this.shenfenzheng = shenfenzheng;
    }

    public String getPws() {
        return pws;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPws(String pws) {
        this.pws = pws;
    }


}
