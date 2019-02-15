package spe_booker.Repositorys;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spe_booker.models.SlotBooking;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public interface SlotBookingRepository extends CrudRepository<SlotBooking, Long> {
}
