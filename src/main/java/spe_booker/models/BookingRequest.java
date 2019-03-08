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
    private Long roomId;

    public BookingRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Long getLength() { return length; }

    public void setLength(Long length) { this.length = length; }

}