package ua.pp.darknsoft.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.pp.darknsoft.models.Location;

public interface LocationRepository extends CrudRepository<Location, Long> {

    Boolean existsLocationByTscNumber(Integer tscNumber);

}
