package ua.pp.darknsoft.services;

import ua.pp.darknsoft.models.Location;

import java.util.Set;

public interface LocationService {

    Set<Location> findAll();

    Location getLocationById(Long id);

    boolean isLocationExist(Location location);

    Location createLocation(Location location);

    Location updateLocation(Location currentLocation);

    void deleteLocationById(Long id);

}
