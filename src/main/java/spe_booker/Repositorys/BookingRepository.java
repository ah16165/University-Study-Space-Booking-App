package spe_booker.Repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spe_booker.models.Booking;
import spe_booker.models.User;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findAll();
    List<Booking> findBookingsByUser(User user);
    List<Booking> findAllByEndDateTimeAfter(Date current);
    List<Booking> findAllByEndDateTimeAfterAndUser(Date current, User user);
    @Transactional
    void deleteAllByUser(User user);
}
