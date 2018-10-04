package ua.pp.darknsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.pp.darknsoft.commands.UserCommand;
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
        Set<AppUser> allUsers = appUserService.findAll();
        model.addAttribute("allUsers", allUsers);
        return "adminPage";
    }

    @GetMapping(value = "/admin/users/create")
    public String createNewUser(Model model) {
        model.addAttribute("usermod", "create_new_user");
        return "adminPage";
    }

    @PostMapping("/admin/users/create")
    @ResponseBody
    public ResponseEntity<UserCommand> addPerson(@RequestBody UserCommand person) {
//        if (userService.isUserExist(person)) {
//            return new ResponseEntity<UserCommand>(person, HttpStatus.CONFLICT);
//        }
        System.out.println("USER: " + person.getUserName());
        return new ResponseEntity<UserCommand>(person, HttpStatus.CREATED);
    }
}
