package ua.pp.darknsoft.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.pp.darknsoft.models.Client;
import ua.pp.darknsoft.services.ClientService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/api")
public class ClientRestController {
    @Autowired
    ClientService clientService;

    @GetMapping(value = "/clients")
    @ResponseBody
    public ResponseEntity<Set<Client>> getAllClient() {

        return new ResponseEntity<Set<Client>>(clientService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/clients/byLastName/{name}")
    @ResponseBody
    public ResponseEntity<Set<Client>> getClientByLastName(@PathVariable(value = "name") String name) {

        return new ResponseEntity<Set<Client>>(clientService.findByFutureAndLastName(name), HttpStatus.OK);

    }

    @GetMapping(value = "/clients/{id}")
    @ResponseBody
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long id) {
        Client currentClient = clientService.getClientById(id);

        if (currentClient == null) {
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Client>(currentClient, HttpStatus.OK);
    }

    @PostMapping(value = "/clients")
    @ResponseBody
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        if (clientService.isClientExist(client)) {
            return new ResponseEntity<Client>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Client>(clientService.createClient(client), HttpStatus.CREATED);
    }

    @PutMapping(value = "/clients/{id}")
    @ResponseBody
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody Client client) {
        Client currentClient = clientService.getClientById(id);
        if (currentClient == null) {
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
        currentClient.setFirstName(client.getFirstName());
        currentClient.setLastName(client.getLastName());
        currentClient.setMiddleName(client.getMiddleName());
        currentClient.setLocation(client.getLocation());
        currentClient.setMeeting(client.getMeeting());
        //currentClient.setAction(client.getAction());
        return new ResponseEntity<Client>(clientService.updateClient(currentClient), HttpStatus.OK);
    }

    @DeleteMapping(value = "/clients/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClientById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/clients/date/{s1}/{s2}/locations/{id}")
    @ResponseBody
    public ResponseEntity<List<Client>> selectAllByMeetingBetweenAndLocationId(@PathVariable(value = "s1") String s1,
                                                                               @PathVariable(value = "s2") String s2,
                                                                               @PathVariable(value = "id") Long id) {
        Date d1 = new Date();
        Date d2 = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            d1 = formatter.parse(s1);
            d2 = formatter.parse(s2);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        List<Client> tmp = clientService.findAllByMeetingBetweenAndLocationId(d1, d2, id);
        if (tmp.isEmpty()) return new ResponseEntity<List<Client>>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<Client>>(tmp, HttpStatus.OK);
    }

    @GetMapping(value = "/clients/date/{dateStr}/locations/{id}")
    @ResponseBody
    public ResponseEntity<List<Client>> selectAllByDate(@PathVariable(value = "dateStr") String dateStr, @PathVariable(value = "id") Long id) {

        List<Client> tmp = null;
        try {
            tmp = clientService.fullDayQueue(dateStr, id);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (tmp.isEmpty()) return new ResponseEntity<List<Client>>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<Client>>(tmp, HttpStatus.OK);
    }

    @GetMapping(value = "/clients/current")
    @ResponseBody
    public ResponseEntity<List<Client>> selectCurentClientsByLocation() {
        List<Client> tmp = clientService.currentClients();
        if (tmp.isEmpty()) return new ResponseEntity<List<Client>>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<Client>>(tmp, HttpStatus.OK);
    }
}
