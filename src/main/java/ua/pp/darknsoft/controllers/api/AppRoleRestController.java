package ua.pp.darknsoft.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.pp.darknsoft.models.AppRole;
import ua.pp.darknsoft.services.AppRoleService;

import java.util.Set;

@Controller
@RequestMapping(value = "/admin/api")
public class AppRoleRestController {
    @Autowired
    AppRoleService appRoleService;

    @GetMapping(value = "/roles")
    public ResponseEntity<Set<AppRole>> getAll() {
        return new ResponseEntity<Set<AppRole>>(appRoleService.findAll(), HttpStatus.OK);
    }


}
