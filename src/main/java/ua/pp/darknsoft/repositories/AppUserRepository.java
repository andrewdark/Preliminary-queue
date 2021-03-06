package ua.pp.darknsoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pp.darknsoft.models.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUserName(String userName);

    Boolean existsAppUsersByUserName(String userNAme);
}
