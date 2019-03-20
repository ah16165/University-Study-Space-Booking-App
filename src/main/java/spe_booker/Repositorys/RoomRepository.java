package spe_booker.Repositorys;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spe_booker.models.Booking;
import spe_booker.models.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
//    @Query("select r from room r inner join booking b on r.id = b.id where b.date_time = :#{#booking.getDateTime()} and b.length = :#{#booking.getLength()}")
//    List<Room> findByDateTimeLength(@Param("booking") Booking booking);
//
//    SELECT roomid FROM rooms where

    List<Room> findAll();
    Optional<Room> findByRoomNoAndBuilding(String roomNo, String building);
}
