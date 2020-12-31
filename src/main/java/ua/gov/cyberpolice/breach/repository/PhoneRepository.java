package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.BankCard;
import ua.gov.cyberpolice.breach.entity.Phone;

import java.util.UUID;

public interface PhoneRepository extends CrudRepository<Phone, UUID> {
}
