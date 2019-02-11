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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
        private int id;

    @Column(name = "email")
    @Email(message = "Enter Email")
    private String email;

    @Column(name = "full name")
    @NotEmpty(message = "Enter full name")
    String name;

    @Column(name = "password")
    @Length(min = 7, message = "Password must be 7 characters")
    @NotEmpty(message = "Enter Password")
    @JsonIgnore
    private String password;

    @Column(name = "faculty")
    @NotEmpty(message = "Enter your faculty")
    private String faculty;

    @Column(name = "year of study")
    @NotEmpty(message = "Enter your year of study")
    private String year;

    @OneToMany(mappedBy = "user")
    private List<SlotBooking> SlotBooking = new ArrayList<>();

        }


