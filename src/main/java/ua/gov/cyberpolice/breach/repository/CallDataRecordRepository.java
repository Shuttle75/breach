package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.CallDataRecord;

import java.util.UUID;

public interface CallDataRecordRepository extends CrudRepository<CallDataRecord, UUID> {
}
