package spe.booker.booker;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private Integer UserID;

    private String Email;

    private String Name;

    private String Faculty;

    private Integer Year;



    public Integer GetUserID() {
        return this.UserID;
    }

    public void SetUserID(Integer UserID) {
        this.UserID = UserID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String Faculty) {
        this.Faculty = Faculty;
    }

    public Integer GetYear() {
        return this.Year;
    }

    public void SetYear(Integer year) {
        this.Year = year;
    }

}