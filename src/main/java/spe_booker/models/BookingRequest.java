package spe_booker.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
public class BookingRequest {

    //Maybe remove repeat?
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Date dateTime;

    @NotNull
    private Long duration;

    @NotNull
    private String roomNo;

    @NotNull
    private String building;

    public BookingRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomId) {
        this.roomNo = roomId;
    }

    public String getBuilding() {return building;}

    public void setBuilding(String building) {this.building = building; }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Long getDuration() { return duration; }

    public void setDuration(Long duration) { this.duration = duration; }

}