package ua.pp.darknsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.models.AppUser;
import ua.pp.darknsoft.repositories.AppUserRepository;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }
}
