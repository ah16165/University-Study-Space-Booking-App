package spe_booker.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String roomNo;

    @NotNull
    private String building;

    @NotNull
    private Integer capacity;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Slot> slots = new ArrayList<>();

    public Room() {

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

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public boolean addSlot(Slot slot) {
        slot.setRoom(this);
        return this.slots.add(slot);
    }

    public boolean removeSlot(Object o) {
        return slots.remove(o);
    }

    public List<Slot> getSlots() {
        return slots;


    }
}
