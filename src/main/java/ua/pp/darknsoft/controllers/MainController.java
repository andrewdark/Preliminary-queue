package ua.pp.darknsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.pp.darknsoft.models.Action;
import ua.pp.darknsoft.services.ClientService;
import ua.pp.darknsoft.services.LocationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        Date d = new Date();

        SimpleDateFormat fIn = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fOut = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        System.out.println(fOut.format(d));

        try {
            model.addAttribute("clients",
                    clientService.findAllByMeetingBetweenAndLocationId(
                            fOut.parse(fIn.format(d) + "T06:00:00.390+0300"), fOut.parse(fIn.format(d) + "T15:00:00.390+0300"), 1L)
            );
            System.out.println(fOut.parse(fIn.format(d) + "T06:00:00.390+0300") + " - " + fOut.parse(fIn.format(d) + "T15:00:00.390+0300"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "clientPage";
    }
}
