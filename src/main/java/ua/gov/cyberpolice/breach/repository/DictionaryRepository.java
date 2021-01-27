package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.gov.cyberpolice.breach.entity.Dictionary;

public interface DictionaryRepository extends CrudRepository<Dictionary, Integer> {
    Iterable<Dictionary> findAllByOrderById();
}
