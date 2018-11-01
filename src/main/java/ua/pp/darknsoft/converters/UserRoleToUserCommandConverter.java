package ua.pp.darknsoft.converters;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ua.pp.darknsoft.commands.UserCommand;
import ua.pp.darknsoft.models.AppRole;
import ua.pp.darknsoft.models.AppUser;
import ua.pp.darknsoft.models.UserRole;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserRoleToUserCommandConverter {
    private final Object $lock = new Object[0];

    @Nullable
    public UserCommand convert(Set<UserRole> userRoles) {
        synchronized ($lock) {
            if (userRoles == null) {
                return null;
            }
            if (userRoles.isEmpty()) {
                return null;
            }
        }
        final UserCommand userCommand = new UserCommand();
        AppUser user = new AppUser();
        user = userRoles.iterator().next().getAppUser();
        userCommand.setUserId(user.getUserId());
        userCommand.setUserName(user.getUserName());
        userCommand.setEnabled(user.getEnabled());
        Set<AppRole> appRoles = new HashSet<>();
        for (UserRole userRole : userRoles) {
            appRoles.add(userRole.getAppRole());
        }
        userCommand.setRoles(appRoles);
        return userCommand;
    }
}
