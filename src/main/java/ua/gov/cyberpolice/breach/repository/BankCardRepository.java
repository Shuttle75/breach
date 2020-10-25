package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.BankCard;

import java.util.UUID;

public interface BankCardRepository extends CrudRepository<BankCard, UUID> {
}
