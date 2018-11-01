package ua.pp.darknsoft.services;

import ua.pp.darknsoft.commands.UserCommand;
import ua.pp.darknsoft.models.UserRole;

import java.util.List;
import java.util.Set;

public interface UserRoleService {

    List<UserRole> findAll();

    UserRole save(UserRole user);

    boolean isUserRoleExist(UserRole userRole);

    UserCommand findByUserId(Long userId);
}
