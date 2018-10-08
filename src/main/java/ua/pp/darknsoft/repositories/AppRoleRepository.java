package ua.pp.darknsoft.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.pp.darknsoft.models.AppRole;

import java.util.Optional;

public interface AppRoleRepository extends CrudRepository<AppRole, Long> {

    Optional<AppRole> findByRoleName(String roleName);
}
