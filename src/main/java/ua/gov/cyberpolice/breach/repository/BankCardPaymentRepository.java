package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.BankCardPayment;

import java.util.UUID;

public interface BankCardPaymentRepository extends CrudRepository<BankCardPayment, UUID> {
}
