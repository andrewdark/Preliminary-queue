package ua.pp.darknsoft.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.pp.darknsoft.models.AppRole;
import ua.pp.darknsoft.services.AppRoleService;

import java.util.Set;

@Controller
@RequestMapping(value = "/admin/api")
public class AppRoleRestController {
    @Autowired
    AppRoleService appRoleService;

    @GetMapping(value = "/roles")
    @ResponseBody
    public ResponseEntity<Set<AppRole>> getAll() {
        return new ResponseEntity<Set<AppRole>>(appRoleService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/roles/{id}")
    @ResponseBody
    public ResponseEntity<AppRole> getAppRoleById(@PathVariable(value = "id") Long id) {
        AppRole appRole = appRoleService.getAppRoleById(id);
        if (appRole == null) {
            return new ResponseEntity<AppRole>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AppRole>(appRole, HttpStatus.OK);
    }

    @GetMapping(value = "/roles/names/{name}")
    @ResponseBody
    public ResponseEntity<AppRole> getAppRoleByRoleName(@PathVariable(value = "name") String name) {
        AppRole appRole = appRoleService.getAppRoleByRoleName(name);
        if (appRole == null) {
            return new ResponseEntity<AppRole>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AppRole>(appRole, HttpStatus.OK);
    }

    @PostMapping(value = "/roles")
    @ResponseBody
    public ResponseEntity<AppRole> createRole(@RequestBody AppRole appRole) {
        if (appRoleService.isAppUserExist(appRole)) {
            return new ResponseEntity<AppRole>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<AppRole>(appRoleService.createAppRole(appRole), HttpStatus.CREATED);
    }

    @PutMapping(value = "/roles/{id}")
    @ResponseBody
    public ResponseEntity<AppRole> updateRole(@PathVariable(value = "id") Long id, @RequestBody AppRole appRole) {
        AppRole currentRole = appRoleService.getAppRoleById(id);
        if (currentRole == null) {
            return new ResponseEntity<AppRole>(HttpStatus.NOT_FOUND);
        }
        currentRole.setRoleName(appRole.getRoleName());
        return new ResponseEntity<AppRole>(appRoleService.updateAppRole(currentRole), HttpStatus.OK
        );
    }

    @DeleteMapping(value = "/roles/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteRole(@PathVariable("id") Long id) {
        appRoleService.deleteAppRoleById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
