package ua.pp.darknsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ua.pp.darknsoft.models.AppRole;
import ua.pp.darknsoft.models.AppUser;
import ua.pp.darknsoft.repositories.AppRoleDAO;
import ua.pp.darknsoft.services.AppRoleService;
import ua.pp.darknsoft.services.AppUserService;
import ua.pp.darknsoft.services.LocationService;
import ua.pp.darknsoft.services.UserRoleService;

import java.util.List;
import java.util.Set;

@Controller
public class AdminController {
    @Autowired
    AppRoleService appRoleService;
    @Autowired
    AppUserService appUserService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    AppRoleDAO appRoleDAO;
    @Autowired
    LocationService locationService;

    @GetMapping(value = "/admin/roles")
    public String adminPageShowRoles(Model model) {
        model.addAttribute("usermod", "roles_show");
        Set<AppRole> allRoles = appRoleService.findAll();
        model.addAttribute("allRoles", allRoles);
        return "adminPage";
    }

    @GetMapping(value = "/admin/users")
    public String adminPageShowUsers(Pageable page, Model model) {
        model.addAttribute("usermod", "users_show");
        Page<AppUser> allUsers = appUserService.getAll(page);
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

    @GetMapping(value = "/admin/users/{id}")
    public String updateUser(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("usermod", "users_detail");
        AppUser appUser = appUserService.getAppUserById(id);
        model.addAttribute("user", appUser);
        List<String> userRoles = appRoleDAO.getRoleNames(id);
        model.addAttribute("userRoles", userRoles);
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("roles", appRoleService.findAll());
        return "adminPage";
    }
}
