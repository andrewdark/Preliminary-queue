package ua.pp.darknsoft.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.pp.darknsoft.models.AppRole;
import ua.pp.darknsoft.models.AppUser;
import ua.pp.darknsoft.models.UserRole;

import java.util.Optional;
import java.util.Set;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    Set<UserRole> findAllUserRoleByAppUser_UserId(Long userId);

    Optional<UserRole> findUserRoleByAppUserAndAndAppRole(AppUser appUser, AppRole appRole);

    Boolean existsUserRoleByAppUserAndAppRole(AppUser appUser, AppRole appRole);
}
