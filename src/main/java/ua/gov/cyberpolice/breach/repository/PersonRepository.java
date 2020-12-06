package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.Person;

import java.util.UUID;

public interface PersonRepository extends CrudRepository<Person, UUID> {
    Person findTopByPassportOrderByModifiedDesc(String passport);
}
