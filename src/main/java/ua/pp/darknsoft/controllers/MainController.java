package ua.pp.darknsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.pp.darknsoft.models.Action;
import ua.pp.darknsoft.services.ClientService;
import ua.pp.darknsoft.services.LocationService;

@Controller
public class MainController {

    @Autowired
    LocationService locationService;
    @Autowired
    ClientService clientService;

    @GetMapping(value = {"/", "/index"})
    public String index() {

        return "index";
    }

    @GetMapping(value = "/createnewclient")
    public String p1(Model model) {
        model.addAttribute("clientmod", "create_new_client");
        model.addAttribute("action", Action.values());
        model.addAttribute("locations", locationService.findAll());
        return "clientPage";
    }

    @GetMapping(value = "/showclients")
    public String showClient(Model model) {
        model.addAttribute("clientmod", "show_all_clients");
        return "clientPage";
    }

    @GetMapping(value = "/operator")
    public String operatorWorkSpace(Model model) {
        model.addAttribute("clientmod", "body_for_operator");
        return "clientPage";
    }

    @GetMapping(value = "/searchclientbylastname")
    public String searchClientByLastName(Model model) {
        model.addAttribute("clientmod", "search_client_by_last_name");
        return "clientPage";
    }
}
