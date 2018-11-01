package ua.pp.darknsoft.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ua.pp.darknsoft.commands.UserCommand;
import ua.pp.darknsoft.models.AppRole;
import ua.pp.darknsoft.models.AppUser;
import ua.pp.darknsoft.models.Location;
import ua.pp.darknsoft.models.UserRole;
import ua.pp.darknsoft.repositories.UserRoleRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserRoleToUserCommandConverter {

    private final Object $lock = new Object[0];

    @Autowired
    UserRoleRepository userRoleRepository;

    @Nullable
    public UserCommand convert(AppUser user) {
        synchronized ($lock) {
            if (user == null) {
                return null;
            }
        }
        Set<UserRole> userRoles = userRoleRepository.findAllUserRoleByAppUser_UserId(user.getUserId());

        final UserCommand userCommand = new UserCommand();


        userCommand.setUserId(user.getUserId());
        userCommand.setUserName(user.getUserName());
        userCommand.setEnabled(user.getEnabled());
        final Location location = new Location();
        if (user.getLocation() != null) {
            location.setId(user.getLocation().getId());
            location.setTscNumber(user.getLocation().getTscNumber());
            location.setAddress(user.getLocation().getAddress());
            userCommand.setLocation(location);
        }
        Set<AppRole> appRoles = new HashSet<>();
        if (userRoles != null & !userRoles.isEmpty()) {
            for (UserRole userRole : userRoles) {
                appRoles.add(userRole.getAppRole());
            }
            userCommand.setRoles(appRoles);
        }
        //userCommand.setUserDetails();


        return userCommand;
    }
}
