package ua.pp.darknsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.pp.darknsoft.models.AppRole;
import ua.pp.darknsoft.models.AppUser;
import ua.pp.darknsoft.services.AppRoleService;
import ua.pp.darknsoft.services.AppUserService;

import java.util.Set;

@Controller
public class AdminController {
    @Autowired
    AppRoleService appRoleService;
    @Autowired
    AppUserService appUserService;

    @GetMapping(value = "/admin/roles")
    public String adminPageShowRoles(Model model) {
        model.addAttribute("usermod", "roles_show");
        Set<AppRole> allRoles = appRoleService.findAll();
        model.addAttribute("allRoles", allRoles);
        return "adminPage";
    }

    @GetMapping(value = "/admin/users")
    public String adminPageShowUsers(Model model) {
        model.addAttribute("usermod", "users_show");
        Set<AppUser> allUsers = appUserService.getAllAppUsers();
        model.addAttribute("allUsers", allUsers);
        //get current user ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = new AppUser();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            appUser = appUserService.getAppUserByUserName(authentication.getName());
        }
        model.addAttribute("userId", appUser.getUserId());
        return "adminPage";
    }

    @GetMapping(value = "/admin/users/create")
    public String createNewUser(Model model) {
        model.addAttribute("usermod", "create_new_user");

        return "adminPage";
    }

}
