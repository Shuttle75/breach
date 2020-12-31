package ua.gov.cyberpolice.breach.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.gov.cyberpolice.breach.entity.BankCard;
import ua.gov.cyberpolice.breach.entity.Breach;
import ua.gov.cyberpolice.breach.entity.Person;
import ua.gov.cyberpolice.breach.entity.Phone;
import ua.gov.cyberpolice.breach.repository.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/breach/{breachId}/phone")
public class PhoneController {
    private static final String VIEWS_PHONE_CREATE_OR_UPDATE_FORM = "breach/createOrUpdatePhoneForm";

    private final BreachRepository breachRepository;
    private final PhoneRepository phoneRepository;
    private final RegionRepository regionRepository;
    private final PersonRepository personRepository;

    public PhoneController(
            BreachRepository breachRepository,
            PhoneRepository phoneRepository,
            RegionRepository regionRepository,
            PersonRepository personRepository) {
        this.phoneRepository = phoneRepository;
        this.breachRepository = breachRepository;
        this.regionRepository = regionRepository;
        this.personRepository = personRepository;
    }

    @ModelAttribute("breach")
    public Breach findBreach(@PathVariable("breachId") UUID breachId) {
        return this.breachRepository.findById(breachId)
                .orElseThrow(() -> new RuntimeException("Not found breachId " + breachId));
    }

    @GetMapping("new/{type}")
    public String initCreationForm(Breach breach, @PathVariable("type") String type, ModelMap model) {
        Phone phone = new Phone();
        Person person = new Person();

        regionRepository.findById("8000000000")
                .ifPresent(person::setRegion);
        if ("used".equals(type)) {
            phone.setBreachId(breach.getId());
        }
        if ("confiscated".equals(type)) {
            phone.setConfiscatedId(breach.getId());
        }

        model.put("phone", phone);
        return VIEWS_PHONE_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("{phoneId}/edit")
    public String initEditForm(@PathVariable("phoneId") UUID phoneId, ModelMap model) {
        phoneRepository.findById(phoneId)
                .ifPresent(phone -> model.put("phone", phone));
        return VIEWS_PHONE_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping({"new/used", "new/confiscated", "{bankCardId}/edit"})
    public String processForm(
            @Valid Phone phone,
            BindingResult result,
            ModelMap model) {
        if (result.hasErrors()) {
            model.put("phone", phone);
            return VIEWS_PHONE_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.phoneRepository.save(phone);
            return "redirect:/breach/{breachId}";
        }
    }

    @GetMapping("{phoneId}/delete")
    public String deleteForm(@PathVariable("phoneId") UUID phoneId) {
        this.phoneRepository.deleteById(phoneId);
        return "redirect:/breach/{breachId}";
    }

    @GetMapping("new/holder/{passport}")
    public ResponseEntity<Person> getPerson(@PathVariable("passport") String passport) {
        return new ResponseEntity<>(personRepository.findTopByPassportOrderByModifiedDesc(passport), HttpStatus.OK);
    }
}
