package spe_booker.Repositorys;

import org.springframework.data.repository.CrudRepository;
import spe_booker.models.SlotBooking;

public interface SlotBookingRepository extends CrudRepository<SlotBooking, Long> {
}