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


//    @Query("select r from room r join booking b on r.id = b.id where b.date_time = :date and b.length = :len ")

    final String QUERY_STRING = "select r from Room r where not exists (select 1 from Booking b where ((b.room.id = r.id) and (:startDateTime < b.endDateTime) and (:endDateTime > b.startDateTime)))";
    @Query(QUERY_STRING)
    List<Room> findAvailable(Date startDateTime, Date endDateTime);

    List<Room> findAll();
    Optional<Room> findByRoomNoAndBuilding(String roomNo, String building);
}







