package spe_booker.Repositorys;

import org.springframework.data.repository.CrudRepository;
import spe_booker.models.Slot;
import org.springframework.beans.factory.annotation.Autowired;


@Repository
public interface SlotRepository extends CrudRepository<Slot, Long> {
}
