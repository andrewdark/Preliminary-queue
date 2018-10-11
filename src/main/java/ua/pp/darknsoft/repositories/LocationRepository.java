package ua.pp.darknsoft.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.pp.darknsoft.models.Location;

import java.util.Optional;

public interface LocationRepository extends CrudRepository<Location, Long> {

}
