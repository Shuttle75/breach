package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.PaymentProvider;

public interface PaymentProviderRepository extends CrudRepository<PaymentProvider, Integer> {
}
