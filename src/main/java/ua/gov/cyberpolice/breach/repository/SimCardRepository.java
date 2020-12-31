package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.Phone;
import ua.gov.cyberpolice.breach.entity.SimCard;

import java.util.UUID;

public interface SimCardRepository extends CrudRepository<SimCard, UUID> {
}
