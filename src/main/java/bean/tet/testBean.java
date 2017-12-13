package bean.tet;


import java.io.Serializable;

public class testBean implements Serializable{
    private String id;
    private String title;
    private String person_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    @Override
    public String toString() {
        return "testBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", person_id='" + person_id + '\'' +
                '}';
    }


}
