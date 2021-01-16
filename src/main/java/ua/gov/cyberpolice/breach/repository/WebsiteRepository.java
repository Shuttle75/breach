package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.WebsitePayment;

import java.util.UUID;

public interface WebsiteRepository extends CrudRepository<WebsitePayment, UUID> {
}
