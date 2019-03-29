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
    private Date startDateTime;

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

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date dateTime) {
        this.startDateTime = startDateTime;
    }

    public Long getDuration() { return duration; }

    public void setDuration(Long duration) { this.duration = duration; }

    public Date getEndDateTime () {
        Date endDateTime = new Date();
        endDateTime.setTime(this.startDateTime.getTime() + (this.duration * 3600000));
        return endDateTime;
    }

}