package ua.pp.darknsoft.services;

import ua.pp.darknsoft.models.AppRole;

import java.util.Set;

public interface AppRoleService {
    AppRole save(AppRole appRole);

    Set<AppRole> findAll();
}
