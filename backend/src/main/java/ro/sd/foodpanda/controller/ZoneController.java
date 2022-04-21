package ro.sd.foodpanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.sd.foodpanda.model.Zone;
import ro.sd.foodpanda.service.ZoneService;

import java.util.List;

@RestController
public class ZoneController {

    @Autowired
    ZoneService zoneService;

    @PostMapping(value = "/addzone")
    public Zone newZone(@RequestBody Zone zone) {
        return zoneService.save(zone);
    }

    @GetMapping("/zones")
    public List<Zone> allZones() {
        return zoneService.findAll();
    }

    @GetMapping("/zones/{name}")
    public Zone findZone(@PathVariable String name) {
        return zoneService.findByName(name);
    }
}
