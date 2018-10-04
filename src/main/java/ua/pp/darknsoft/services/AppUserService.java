package ua.pp.darknsoft.services;

import ua.pp.darknsoft.models.AppRole;
import ua.pp.darknsoft.models.AppUser;

import java.util.Set;

public interface AppUserService {
    AppUser save(AppUser appUser);

    Set<AppUser> findAll();
}
