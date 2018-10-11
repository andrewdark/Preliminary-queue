package ua.pp.darknsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.models.Location;
import ua.pp.darknsoft.repositories.LocationRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    LocationRepository locationRepository;

    @Override
    public Set<Location> findAll() {
        Set<Location> tmp = new HashSet<>();
        for (Location loc : locationRepository.findAll()) {
            tmp.add(loc);
        }
        return tmp;
    }

    @Override
    public Location getLocationById(Long id) {
        Optional<Location> locationOptional = locationRepository.findById(id);
        if (!locationOptional.isPresent()) return null;
        return locationOptional.get();
    }

    @Override
    public boolean isLocationExist(Location location) {
        return findAll().contains(location);
    }

    @Transactional
    @Override
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    @Transactional
    @Override
    public Location updateLocation(Location currentLocation) {
        return locationRepository.save(currentLocation);
    }

    @Override
    public void deleteLocationById(Long id) {
        locationRepository.deleteById(id);
    }
}
