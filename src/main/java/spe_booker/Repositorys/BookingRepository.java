package spe_booker.Repositorys;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

import org.springframework.stereotype.Repository;
import spe_booker.models.Booking;
import spe_booker.models.Room;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findAll();
}
