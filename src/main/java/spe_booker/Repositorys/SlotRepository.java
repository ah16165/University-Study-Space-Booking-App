package spe_booker.Repositorys;

import org.springframework.data.repository.CrudRepository;
import spe_booker.models.Slot;

public interface SlotRepository extends CrudRepository<Slot, Long> {
}