package spe_booker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "user")
public class User{
    @Id
    @GeneratedValue
//    @Column(name = "id")
        private Long id;
//
//    @Column(name = "email")
//    @Email(message = "Enter Email")
    private String username;

//    @Column(name = "full name")
//    @NotEmpty(message = "Enter full name")
    String name;

//    @Column(name = "password")
//    @Length(min = 7, message = "Password must be 7 characters")
//    @NotEmpty(message = "Enter Password")
//    @JsonIgnore
    private String password;

//    @Column(name = "faculty")
//    @NotEmpty(message = "Enter your faculty")
    private String faculty;

//    @Column(name = "role of study")
//    @NotEmpty(message = "Enter your role of study")
    private String role;

    @Column(name = "enabled")
    public int enabled;

    @OneToMany(mappedBy = "user")
    private List<SlotBooking> SlotBooking = new ArrayList<>();

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

    public void setRole(String role) {
        this.role = role;
    }
}


