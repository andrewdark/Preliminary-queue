package ua.pp.darknsoft.services;

import ua.pp.darknsoft.models.AppRole;

import java.util.Set;

public interface AppRoleService {

    AppRole createAppRole(AppRole appRole);

    Set<AppRole> findAll();

    AppRole getAppRoleById(Long id);

    AppRole getAppRoleByRoleName(String name);

    boolean isAppUserExist(AppRole appRole);

    AppRole updateAppRole(AppRole currentRole);

    void deleteAppRoleById(Long id);
}
