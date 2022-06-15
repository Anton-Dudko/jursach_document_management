package model;

public class Document {
    private int id;
    private String name;
    private String date;
    private String about;
    private String check;

    public Document(int id, String name, String date, String about, String check) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.about = about;
        this.check = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", about='" + about + '\'' +
                ", check='" + check + '\'' +
                '}';
    }
}
