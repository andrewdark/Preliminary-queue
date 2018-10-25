package ua.pp.darknsoft.services;

import ua.pp.darknsoft.models.UserRole;

import java.util.List;

public interface UserRoleService {

    List<UserRole> findAll();

    UserRole save(UserRole user);

    boolean isUserRoleExist(UserRole userRole);
}
