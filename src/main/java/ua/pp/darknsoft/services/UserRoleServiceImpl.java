package ua.pp.darknsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.models.UserRole;
import ua.pp.darknsoft.repositories.UserRoleRepository;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserRole save(UserRole user) {
        return userRoleRepository.save(user);
    }
}
