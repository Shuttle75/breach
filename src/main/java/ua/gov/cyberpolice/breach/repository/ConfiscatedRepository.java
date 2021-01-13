package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.Confiscated;

import java.util.UUID;

public interface ConfiscatedRepository extends CrudRepository<Confiscated, UUID> {
}
