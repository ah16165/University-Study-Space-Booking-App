package spe_booker.Repositorys;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import spe_booker.models.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    List<Room> findAll();
    Optional<Room> findByRoomNoAndBuilding(String roomNo, String building);
}
