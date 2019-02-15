package spe_booker.Repositorys;

import org.springframework.data.repository.CrudRepository;
import spe_booker.models.SlotBooking;
import org.springframework.beans.factory.annotation.Autowired;


@Repository
public interface SlotBookingRepository extends CrudRepository<SlotBooking, Long> {
}
