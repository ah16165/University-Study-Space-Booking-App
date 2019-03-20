package spe_booker.Repositorys;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import spe_booker.models.Booking;
import spe_booker.models.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    @Query("select r from room r inner join booking b on r.id = b.id where b.date_time = :#{#booking.dateTime} and b.length = :#{#booking.length}")
    List<Room> findByDateTimeLength(@Param("booking") Booking booking);

    //where b.date_time = :dateTime and b.length = :length)
    //List<Room> findByDateTimeLength(@Param("dateTime") Date dateTime,
    //                             @Param("length") Long length);
}
    List<Room> findAll();
    Optional<Room> findByRoomNoAndBuilding(String roomNo, String building);
}
