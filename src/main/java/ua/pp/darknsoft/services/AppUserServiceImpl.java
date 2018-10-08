package ua.pp.darknsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.models.AppUser;
import ua.pp.darknsoft.repositories.AppUserRepository;
import ua.pp.darknsoft.utils.EncryptedPasswordUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    AppUserRepository appUserRepository;

    @Transactional
    @Override
    public AppUser createAppUser(AppUser appUser) {
        appUser.setEncryptedPassword(EncryptedPasswordUtils.encryptPassword(appUser.getEncryptedPassword()));
        return appUserRepository.save(appUser);
    }

    @Override
    public Set<AppUser> getAllAppUsers() {
        Set<AppUser> tmpSet = new HashSet<>();
        for (AppUser user : appUserRepository.findAll()) {
            tmpSet.add(user);
        }
        return tmpSet;
    }

    @Override
    public AppUser getAppUserById(Long id) {
        try {
            Optional<AppUser> appUserOptional = appUserRepository.findById(id);
        } catch (java.util.NoSuchElementException ex) {
            return null;
        }
        return appUserRepository.findById(id).get();
    }

    @Override
    public AppUser getAppUserByUserName(String userName) {
        Optional<AppUser> appUserOptional = appUserRepository.findByUserName(userName);
        if (!appUserOptional.isPresent()) {
            return null;
        } else return appUserOptional.get();
    }

    @Override
    public boolean isAppUserExist(AppUser appUser) {
        return getAllAppUsers().contains(appUser);
    }

    @Transactional
    @Override
    public AppUser updateAppUser(AppUser appUser) {
        appUser.setEncryptedPassword(EncryptedPasswordUtils.encryptPassword(appUser.getEncryptedPassword()));
        return appUserRepository.save(appUser);
    }

    @Override
    public void deleteAppUserById(Long id) {
        appUserRepository.deleteById(id);
    }
}
