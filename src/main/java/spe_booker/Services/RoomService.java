package spe_booker.Services;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spe_booker.Repositorys.RoomRepository;
import spe_booker.Repositorys.UserRepository;
import spe_booker.models.Booking;
import spe_booker.models.Room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class RoomService {


    private RoomRepository roomRepository;


    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;

    }

    public List<Room> findAll(){
        return roomRepository.findAll();
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
        save(s);
        return s;
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }

//    public List<Pair<Room, Integer>> getRoomsAndNoOfBookings(){
//        List<Pair<Room, Integer>> roomsAndNoOfBookings = new ArrayList<>();
//        List<Room> rooms = roomRepository.findAll();
//        for (Room room : rooms){
//            roomsAndNoOfBookings.add(Pair.with(room, room.))
//        }
//        return roomsAndNoOfBookings;
//    }


    public List<Room> getRoomsAndNoBookingsForLastWeek(){
        List<Room> rooms = findAll();
        Date currentDate = new Date();
        Date lastWeek = new Date();
        lastWeek.setTime((long) currentDate.getTime()-604800000);
        for (Room room : rooms){
            List<Booking> bookings = room.getBookings();
            for (Booking booking : bookings){
                if (booking.getStartDateTime().before(lastWeek)){
                    bookings.remove(booking);
                }
            }
            room.setBookings(bookings);
        }
        return rooms;
    }

//    public List<Room> findAvailable(Date date, Long duration){
//        return roomRepository.findAvailable(date, duration);
//    }
}
