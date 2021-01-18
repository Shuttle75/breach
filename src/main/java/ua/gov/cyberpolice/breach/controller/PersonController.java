package ua.gov.cyberpolice.breach.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.gov.cyberpolice.breach.entity.Person;
import ua.gov.cyberpolice.breach.repository.PersonRepository;

@Controller
@RequestMapping
public class PersonController {
    private final PersonRepository personRepository;

    public PersonController(
            PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping({"breach/{breachId}/person/{passport}",
            "breach/{breachId}/confiscated/{confiscatedId}/person/{passport}"})
    public ResponseEntity<Person> getPerson(@PathVariable("passport") String passport) {
        return new ResponseEntity<>(personRepository.findTopByPassportOrderByModifiedDesc(passport), HttpStatus.OK);
    }
}
