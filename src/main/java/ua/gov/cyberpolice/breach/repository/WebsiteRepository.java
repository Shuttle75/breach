package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.Website;

import java.util.UUID;

public interface WebsiteRepository extends CrudRepository<Website, UUID> {
}
