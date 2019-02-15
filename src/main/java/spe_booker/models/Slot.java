package spe_booker.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
public class Slot {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Integer numberOfStudents;

    @NotNull
    private Integer hoursOfSlot;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date dateTime;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Room room;

    @OneToMany(mappedBy = "slot", cascade = CascadeType.ALL)
    private Collection<SlotBooking> slotBookings = new ArrayList<>();



    public Slot(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Collection<SlotBooking> getSlotBooking() {
        return slotBookings;
    }

    public Integer getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(Integer numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public Integer gethoursOfSlot() {
        return hoursOfSlot;
    }

    public void sethoursOfSlot(Integer hoursOfSlot) {
        this.hoursOfSlot = hoursOfSlot;
    }
}
