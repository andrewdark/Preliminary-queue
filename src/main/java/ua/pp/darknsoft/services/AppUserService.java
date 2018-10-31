package ua.pp.darknsoft.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.pp.darknsoft.models.AppUser;

import java.util.Set;

public interface AppUserService {

    Set<AppUser> getAllAppUsers();

    Page<AppUser> getAll(Pageable page);

    AppUser getAppUserById(Long id);

    AppUser getAppUserByUserName(String userName);

    AppUser createAppUser(AppUser appUser);

    AppUser updateAppUser(AppUser currentUser);

    void deleteAppUserById(Long id);

    boolean isAppUserExist(AppUser appUser);
}
