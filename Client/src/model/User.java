package model;

public class User {
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String roll;




  public static  User  currentUser;


    public User(int id, String name, String lastName, String email, String password,  String roll) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

        this.roll = roll;
    }

    public User(String email, String password, String roll) {
        this.email = email;
        this.password = password;
        this.roll = roll;
    }

    public User(int id, String email, String password,String roll) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roll = roll;
    }

    public User() {

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roll='" + roll + '\'' +
                '}';
    }
}
