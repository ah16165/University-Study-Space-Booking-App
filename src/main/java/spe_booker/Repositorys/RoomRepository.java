package spe_booker.Repositorys;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

import org.springframework.stereotype.Repository;
import spe_booker.models.Room;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    List<Room> findAll();
}
