package spe_booker.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;


@Entity
public class Booking {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Date dateTime;


    @NotNull
    private Long length;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Room room;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    private Date creationDate;

    public Booking() {  }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Long getLength() { return length; }

    public void setLength(Long length) { this.length = length; }

    public Date getCreationDate(){
        System.out.print("######Creation Date = " + creationDate + "\n");
        return creationDate; }

    public void setCreationDate(Date creationDate) { this.creationDate = creationDate;}


}
