package bean.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Table(name="check_in_table")
public class WeCheckInEntity implements Serializable{

    //INSERT INTO `daida`.
    // `check-in-table` (`detailed_information`, `latitude`, `longitude`, `street_number`, `id`, `openid`, `add_time`)
    // VALUES (NULL, NULL, NULL, NULL, NULL, NULL, NULL);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Id
    private Integer id;
    @Column
    private String detailed_information;
    @Column
    private String latitude;
    @Column
    private String longitude;
    @Column
    private String street_number;
    @Column
    private String openid;
    @Column
    private Date add_time;

    @Override
    public String toString() {
        return "WeCheckInEntity{" +
                "id=" + id +
                ", detailed_information='" + detailed_information + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", street_number='" + street_number + '\'' +
                ", openid='" + openid + '\'' +
                ", add_time=" + sdf.format(add_time) +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetailed_information() {
        return detailed_information;
    }

    public void setDetailed_information(String detailed_information) {
        this.detailed_information = detailed_information;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }
}
