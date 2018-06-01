package bean.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@Table(name = "weUser")
public class WeUserEntity implements Serializable{
    //INSERT INTO `daida`.`weUser` (`openid`, `nickName`, `gender`, `language`, `city`, `province`, `country`, `avatarUrl`, `phone`)
    // VALUES (NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

    /**
     * org.springframework.jdbc.BadSqlGrammarException:   com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException
     *当自己添加的字段中需要驼峰命名的时候 tk.mybitis会出现自动将大写字段 例如daiDa   改成dai_da这样一来数据库字段和实体类字段
     * 包括方法中都会出现问题所以在使用tk.mybaits的时候需要程序员将字段标准化  xxx_xxx
     *
     * INSERT INTO weUser  ( openid,nick_name,gender,language,city,province,country,avatar_url,phone ) VALUES( ?,?,?,?,?,?,?,?,? )
     */
    @Id
    private String openid;
    @Column
    private String nick_name;
    @Column
    private String gender;
    @Column
    private String language;
    @Column
    private String city;
    @Column
    private String province;
    @Column
    private String country;
    @Column
    private String avatar_url;
    @Column
    private String phone;
    @Column
    private Date up_time;

    public Date getUp_time() {
        return up_time;
    }

    public void setUp_time(Date up_time) {
        this.up_time = up_time;
    }

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "WeUserEntity{" +
                "openid='" + openid + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", gender='" + gender + '\'' +
                ", language='" + language + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", phone='" + phone + '\'' +
                ", add_time=" + add_time +
                '}';
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    @Column
    private Date add_time;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
