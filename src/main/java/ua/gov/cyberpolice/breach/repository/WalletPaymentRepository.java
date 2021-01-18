package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.WalletPayment;

import java.util.UUID;

public interface WalletPaymentRepository extends CrudRepository<WalletPayment, UUID> {
}
