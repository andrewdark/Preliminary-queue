package ua.pp.darknsoft.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.pp.darknsoft.models.Location;
import ua.pp.darknsoft.services.LocationService;

import java.util.Set;

@Controller
@RequestMapping(value = "/api")
public class LocationRestController {
    @Autowired
    LocationService locationService;

    @GetMapping(value = "/locations")
    @ResponseBody
    public ResponseEntity<Set<Location>> getAllLocations() {

        return new ResponseEntity<Set<Location>>(locationService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/location/{id}")
    @ResponseBody
    public ResponseEntity<Location> getLocationById(@PathVariable(value = "id") Long id) {
        Location currentLocation = locationService.getLocationById(id);

        if (currentLocation == null) {
            return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Location>(currentLocation, HttpStatus.OK);
    }

    @PostMapping(value = "/locations")
    @ResponseBody
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        if (locationService.isLocationExist(location)) {
            return new ResponseEntity<Location>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Location>(locationService.createLocation(location), HttpStatus.CREATED);
    }

    @PutMapping(value = "/locations/{id}")
    @ResponseBody
    public ResponseEntity<Location> updateLocation(@PathVariable("id") Long id, @RequestBody Location location) {
        Location currentLocation = locationService.getLocationById(id);
        if (currentLocation == null) {
            return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
        }

        //currentClient.setAction(client.getAction());
        return new ResponseEntity<Location>(locationService.updateLocation(currentLocation), HttpStatus.OK);
    }

    @DeleteMapping(value = "/locations/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteLocation(@PathVariable("id") Long id) {
        locationService.deleteLocationById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
