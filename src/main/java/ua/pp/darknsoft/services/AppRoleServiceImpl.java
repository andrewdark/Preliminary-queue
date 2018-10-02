package ua.pp.darknsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.models.AppRole;
import ua.pp.darknsoft.repositories.AppRoleRepository;

@Service
public class AppRoleServiceImpl implements AppRoleService {

    @Autowired
    AppRoleRepository roleRepository;

    @Override
    public AppRole save(AppRole appRole) {
        return roleRepository.save(appRole);
    }
}
