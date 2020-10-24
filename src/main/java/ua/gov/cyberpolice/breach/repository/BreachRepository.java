package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.Breach;

import java.util.List;
import java.util.UUID;

public interface BreachRepository extends CrudRepository<Breach, UUID> {
    List<Breach> findAll();

}
