package ua.pp.darknsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.pp.darknsoft.services.AppRoleService;
import ua.pp.darknsoft.services.AppUserService;
import ua.pp.darknsoft.services.UserRoleService;
import ua.pp.darknsoft.utils.WebUtils;

import java.security.Principal;

@Controller
public class AuthController {
    @Autowired
    AppUserService appUserService;
    @Autowired
    AppRoleService appRoleService;
    @Autowired
    UserRoleService userRoleService;

    @GetMapping(value = "/login")
    public String loginPage() {

        return "login";
    }

    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }

    @GetMapping(value = "/infoPage")
    public String createRole(Model model) {
        String message = "DONE";
//        AppRole appRole = new AppRole();
//        //appRole.setRoleId(2L);
//        appRole.setRoleName("ROLE_ADMIN");
//        AppUser appUser = new AppUser();
//        appUser.setUserName("dark");
//        appUser.setEncryptedPassword("$2a$10$xrjVTRL4n0Tq44GGSs0ZU.L/oIIhs9FkYTj.JCJoMb9lLvlCu.ykq");
//        appUser.setEnabled(true);
//        UserRole userRole = new UserRole();
//
//        try {
//            AppRole sRole = appRoleService.createAppUser(appRole);
//            AppUser sUser = appUserService.createAppUser(appUser);
//            userRole.setAppRole(sRole);
//            userRole.setAppUser(sUser);
//            userRoleService.createAppUser(userRole);
//        } catch (Exception ex) {
//            message = "BAD NEWS ;-( <br />" + ex.toString();
//        }
        model.addAttribute("message", message);
        return "infoPage";
    }
}
