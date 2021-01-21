package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.BreachType;

public interface BreachTypeRepository extends CrudRepository<BreachType, Integer> {
}
