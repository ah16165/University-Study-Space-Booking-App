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
    private Date startDateTime;

    @NotNull
    private Date endDateTime;


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

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public long getDuration() {
        return ((endDateTime.getTime() - startDateTime.getTime()) / 3600000);

    }
    public Date getCreationDate(){
        System.out.print("######Creation Date = " + creationDate + "\n");
        return creationDate; }

    public void setCreationDate(Date creationDate) { this.creationDate = creationDate;}


}
