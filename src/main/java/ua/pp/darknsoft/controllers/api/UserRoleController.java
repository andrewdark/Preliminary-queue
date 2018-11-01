package ua.pp.darknsoft.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.pp.darknsoft.commands.UserCommand;
import ua.pp.darknsoft.models.UserRole;
import ua.pp.darknsoft.services.UserRoleService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/api")
public class UserRoleController {
    @Autowired
    UserRoleService userRoleService;

    @GetMapping(value = "/user_role")
    public ResponseEntity<List<UserRole>> findAll() {
        List<UserRole> userRoles = userRoleService.findAll();
        for (int i = 0; i < userRoles.size(); i++) {
            userRoles.get(i).getAppUser();
            userRoles.get(i).getAppRole();
        }
        return new ResponseEntity<List<UserRole>>(userRoles, HttpStatus.OK);
    }

    @GetMapping(value = "/user_role/users/{userId}")
    public ResponseEntity<UserCommand> findRolesByUserId(@PathVariable(value = "userId") Long userId) {
        UserCommand userCommand = userRoleService.findByUserId(userId);
        if (userCommand == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserCommand>(userCommand, HttpStatus.OK);
    }

    @PostMapping(value = "/user_role")
    public ResponseEntity<UserRole> addRoleToUser(@RequestBody UserRole userRole) {
        if (userRoleService.isUserRoleExist(userRole)) {
            return new ResponseEntity<UserRole>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<UserRole>(userRoleService.save(userRole), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/user_role/{userId}/{roleId}")
    public ResponseEntity<Void> deleteUserFromRole(@PathVariable(value = "userId") Long userId,
                                                   @PathVariable(value = "roleId") Long roleId) {
        userRoleService.deleteUserFromRole(userId, roleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
