package ua.pp.darknsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.commands.UserCommand;
import ua.pp.darknsoft.converters.UserRoleToUserCommandConverter;
import ua.pp.darknsoft.models.UserRole;
import ua.pp.darknsoft.repositories.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    UserRoleToUserCommandConverter userRoleConverter;

    @Override
    public List<UserRole> findAll() {
        List<UserRole> tmpList = new ArrayList<>();
        for (UserRole uR : userRoleRepository.findAll()) {
            tmpList.add(uR);
        }
        return tmpList;
    }

    @Override
    public UserRole save(UserRole user) {
        return userRoleRepository.save(user);
    }

    @Override
    public boolean isUserRoleExist(UserRole userRole) {

        return findAll().contains(userRole);
    }

    @Override
    public UserCommand findByUserId(Long userId) {
        Optional<UserCommand> userCommand = Optional.ofNullable(userRoleConverter.convert(userRoleRepository.findAllUserRoleByAppUser_UserId(userId)));
        if (!userCommand.isPresent()) {
            System.out.println("User with id: " + userId + " NOT found");
            return null;
        }
        return userCommand.get();
    }
}
