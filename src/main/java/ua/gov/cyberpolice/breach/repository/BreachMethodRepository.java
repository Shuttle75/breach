package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.BreachMethod;

public interface BreachMethodRepository extends CrudRepository<BreachMethod, Integer> {
    Iterable<BreachMethod> findAllByOrderById();
}
