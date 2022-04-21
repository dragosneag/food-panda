package ro.sd.foodpanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.foodpanda.model.User;
import ro.sd.foodpanda.model.Zone;
import ro.sd.foodpanda.repository.UserRepository;
import ro.sd.foodpanda.repository.ZoneRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    public Zone save(Zone zone) {
        return zoneRepository.save(zone);
    }

    public Zone findByName(String name) {
        return zoneRepository.findZoneByName(name);
    }

    public List<Zone> findAll() {
        List<Zone> zones = new ArrayList<>();
        Iterable<Zone> all = zoneRepository.findAll();
        all.forEach(zones::add);
        return zones;
    }
}
