package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.Prison;

public interface PrisonRepository extends CrudRepository<Prison, Integer> {
    Iterable<Prison> findAllByOrderById();
}
