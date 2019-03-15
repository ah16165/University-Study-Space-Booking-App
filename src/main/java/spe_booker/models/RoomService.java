package spe_booker.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spe_booker.Repositorys.RoomRepository;
import spe_booker.Repositorys.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
public class RoomService {


    private RoomRepository roomRepository;


    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;

    }


    public Optional<Room> findByRoomNoAndBuilding(String roomNo, String building) {
        return roomRepository.findByRoomNoAndBuilding(roomNo, building);
    }

    public Room createRoom(String roomNo, String building, Integer capacity, List<Booking> bookings) {
        Room s = new Room();

        s.setRoomNo(roomNo);
        s.setBuilding(building);
        s.setCapacity(capacity);
        s.setBookings(bookings);
        saveRoom(s);

        return s;
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }



}
