package ua.pp.darknsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.models.AppRole;
import ua.pp.darknsoft.repositories.AppRoleRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class AppRoleServiceImpl implements AppRoleService {

    @Autowired
    AppRoleRepository roleRepository;

    @Override
    public AppRole save(AppRole appRole) {
        return roleRepository.save(appRole);
    }

    @Override
    public Set<AppRole> findAll() {
        Set<AppRole> tmpSet = new HashSet<>();
        for (AppRole role : roleRepository.findAll()) {
            tmpSet.add(role);
        }
        return tmpSet;
    }
}
