package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ua.gov.cyberpolice.breach.entity.Breach;

import java.util.UUID;

public interface BreachRepository extends JpaRepository<Breach, UUID>, QuerydslPredicateExecutor<Breach> {
}
