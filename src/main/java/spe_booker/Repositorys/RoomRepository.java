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

    //@Query("select r from rooms r where not exists (select 1 from bookings b where b.room_id = r.room_id and @input_date_time < date_add(b.date_time, interval length 1)) and date_add(@input_date_time, interval @input_length 1) > b.date_time")
    //List<Room> findByDateTimeLength(@Param("input_date_time") Date date, @Param("input_length") Long length);

    List<Room> findAll();
    Optional<Room> findByRoomNoAndBuilding(String roomNo, String building);
}





