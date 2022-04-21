package ro.sd.foodpanda.repository;

import ro.sd.foodpanda.model.Zone;

public interface ZoneRepository extends AbstractRepository<Zone> {

    Zone findZoneByName(String name);
}
