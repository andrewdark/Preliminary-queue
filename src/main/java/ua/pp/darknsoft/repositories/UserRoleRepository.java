package ua.pp.darknsoft.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.pp.darknsoft.models.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
}
