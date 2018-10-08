package ua.pp.darknsoft.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.pp.darknsoft.models.AppUser;
import ua.pp.darknsoft.services.AppUserService;

import java.util.Set;

@Controller
@RequestMapping(value = "/admin/api")
public class AppUserRestController {

    @Autowired
    AppUserService appUserService;

    @GetMapping(value = "/users")
    @ResponseBody
    public ResponseEntity<Set<AppUser>> getAllUsers() {
        return new ResponseEntity<Set<AppUser>>(appUserService.getAllAppUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}")
    @ResponseBody
    public ResponseEntity<AppUser> getUserById(@PathVariable("id") Long id) {
        AppUser appUser = appUserService.getAppUserById(id);
        if (appUser == null) {
            return new ResponseEntity<AppUser>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AppUser>(appUser, HttpStatus.OK);
    }

    @GetMapping(value = "/users/names/{name}")
    @ResponseBody
    public ResponseEntity<AppUser> getUserByUserName(@PathVariable("name") String name) {
        AppUser appUser = appUserService.getAppUserByUserName(name);
        if (appUser == null) {
            return new ResponseEntity<AppUser>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AppUser>(appUser, HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    @ResponseBody
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser) {
        if (appUserService.isAppUserExist(appUser)) {
            return new ResponseEntity<AppUser>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<AppUser>(appUserService.createAppUser(appUser), HttpStatus.CREATED);
    }

    @PutMapping(value = "/users/{id}")
    @ResponseBody
    public ResponseEntity<AppUser> updateUser(@PathVariable("id") Long id, @RequestBody AppUser appUser) {
        AppUser currentUser = appUserService.getAppUserById(id);
        if (currentUser == null) {
            return new ResponseEntity<AppUser>(HttpStatus.NOT_FOUND);
        }
        currentUser.setUserName(appUser.getUserName());
        currentUser.setEncryptedPassword(appUser.getEncryptedPassword());
        currentUser.setEnabled(appUser.getEnabled());
        return new ResponseEntity<AppUser>(appUserService.updateAppUser(currentUser), HttpStatus.OK
        );
    }

    @DeleteMapping(value = "/users/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        appUserService.deleteAppUserById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
