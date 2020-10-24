package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.Method;

public interface MethodRepository extends CrudRepository<Method, Integer> {
}
