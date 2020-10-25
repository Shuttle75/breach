package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.Region;

public interface RegionRepository extends CrudRepository<Region, String> {
}
