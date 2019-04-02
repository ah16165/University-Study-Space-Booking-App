package spe_booker.Repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spe_booker.models.Booking;
import spe_booker.models.User;

import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findAll();
    List<Booking> findBookingsByUser(User user);
}
