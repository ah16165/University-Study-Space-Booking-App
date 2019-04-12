package spe_booker.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
        private Long id;

    private String username;

    String name;

    private String password;

    private String faculty;

    private String role;

    private Boolean blacklisted;

    @Column(name = "enabled")
    public int enabled;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getRole() {
        return role;
    }

    public Boolean isAdmin(){ return role.equals("admin"); }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getBlacklisted() {return blacklisted;}

    public void setBlacklisted(Boolean blacklisted) {this.blacklisted = blacklisted;}

    public Integer getNumberOfBookings(){
        return bookings.size();
    }

}


