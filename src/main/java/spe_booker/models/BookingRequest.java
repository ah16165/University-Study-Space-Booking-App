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
    private Long length;

    @NotNull
    private Long roomNo;

    @NotNull
    private Long building;

    public BookingRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Long roomId) {
        this.roomNo = roomId;
    }

    public Long getBuilding() {return building;}

    public void setBuilding(Long building) {this.building = building; }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Long getLength() { return length; }

    public void setLength(Long length) { this.length = length; }

}