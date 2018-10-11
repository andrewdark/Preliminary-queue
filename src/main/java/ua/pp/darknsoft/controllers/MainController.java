package ua.pp.darknsoft.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = {"/", "/index"})
    public String index() {

        return "index";
    }

    @GetMapping(value = "/test1")
    public String p1(Model model){
        model.addAttribute("clientmod", "create_new_client");
        return "clientPage";
    }
}
