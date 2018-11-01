package ua.pp.darknsoft.services;

import ua.pp.darknsoft.commands.UserCommand;
import ua.pp.darknsoft.models.UserRole;

import java.util.List;

public interface UserRoleService {

    List<UserRole> findAll();

    UserRole save(UserRole user);

    boolean isUserRoleExist(UserRole userRole);

    UserCommand findByUserId(Long userId);

    void deleteUserFromRole(Long userId, Long roleId);
}
