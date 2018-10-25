package ua.pp.darknsoft.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.pp.darknsoft.models.AppRole;
import ua.pp.darknsoft.models.AppUser;
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
        for(int i = 0; i<userRoles.size(); i++){
            userRoles.get(i).getAppUser();
            userRoles.get(i).getAppRole();
        }
        return new ResponseEntity<List<UserRole>>(userRoles, HttpStatus.OK);
    }

    @PostMapping(value = "/user_role")
    public ResponseEntity<UserRole> addRoleToUser(@RequestBody UserRole userRole) {
        if (userRoleService.isUserRoleExist(userRole)) {
            return new ResponseEntity<UserRole>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<UserRole>(userRoleService.save(userRole), HttpStatus.CREATED);
    }
}
