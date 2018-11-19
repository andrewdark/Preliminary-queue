package ua.pp.darknsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.models.AppRole;
import ua.pp.darknsoft.repositories.AppRoleRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppRoleServiceImpl implements AppRoleService {

    @Autowired
    AppRoleRepository roleRepository;

    @Transactional
    @Override
    public AppRole createAppRole(AppRole appRole) {
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

    @Override
    public AppRole getAppRoleById(Long id) {
        Optional<AppRole> appRoleOptional = roleRepository.findById(id);
        if (!appRoleOptional.isPresent()) {
            return null;
        }
        return appRoleOptional.get();
    }

    @Override
    public AppRole getAppRoleByRoleName(String name) {
        Optional<AppRole> appRoleOptional = roleRepository.findByRoleName(name);
        if (!appRoleOptional.isPresent()) {
            return null;
        }
        return appRoleOptional.get();
    }

    @Override
    public boolean isAppUserExist(AppRole appRole) {
        return roleRepository.existsAppRoleByRoleName(appRole.getRoleName());
    }

    @Transactional
    @Override
    public AppRole updateAppRole(AppRole currentRole) {
        return roleRepository.save(currentRole);
    }

    @Override
    public void deleteAppRoleById(Long id) {
        roleRepository.deleteById(id);
    }
}
