package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.Prison;

import java.util.UUID;

public interface PrisonRepository extends CrudRepository<Prison, UUID> {
}
