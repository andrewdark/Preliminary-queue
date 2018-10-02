package ua.pp.darknsoft.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.pp.darknsoft.models.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
}
