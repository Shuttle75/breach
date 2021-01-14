package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.Wallet;

import java.util.UUID;

public interface WalletRepository extends CrudRepository<Wallet, UUID> {
}
