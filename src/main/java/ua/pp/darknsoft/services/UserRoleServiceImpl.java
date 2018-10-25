package ua.pp.darknsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.models.UserRole;
import ua.pp.darknsoft.repositories.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

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
}
