package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.ParticipantType;

public interface ParticipantTypeRepository extends CrudRepository<ParticipantType, Integer> {
}
