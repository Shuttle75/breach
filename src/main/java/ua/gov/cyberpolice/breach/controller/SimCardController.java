package ua.gov.cyberpolice.breach.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.gov.cyberpolice.breach.entity.Breach;
import ua.gov.cyberpolice.breach.entity.Person;
import ua.gov.cyberpolice.breach.entity.SimCard;
import ua.gov.cyberpolice.breach.repository.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/breach/{breachId}/sim-card")
public class SimCardController {
    private static final String VIEWS_SIM_CARD_CREATE_OR_UPDATE_FORM = "breach/createOrUpdateSimCardForm";

    private final BreachRepository breachRepository;
    private final SimCardRepository simCardRepository;
    private final RegionRepository regionRepository;
    private final PersonRepository personRepository;

    public SimCardController(
            BreachRepository breachRepository,
            SimCardRepository simCardRepository,
            RegionRepository regionRepository,
            PersonRepository personRepository) {
        this.simCardRepository = simCardRepository;
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
        SimCard simCard = new SimCard();
        Person person = new Person();

        regionRepository.findById("8000000000")
                .ifPresent(person::setRegion);
        if ("used".equals(type)) {
            simCard.setBreachId(breach.getId());
        }
        if ("confiscated".equals(type)) {
            simCard.setConfiscatedId(breach.getId());
        }

        model.put("simCard", simCard);
        return VIEWS_SIM_CARD_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("{simCardId}/edit")
    public String initEditForm(@PathVariable("simCardId") UUID simCardId, ModelMap model) {
        simCardRepository.findById(simCardId)
                .ifPresent(simCard -> model.put("simCard", simCard));
        return VIEWS_SIM_CARD_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping({"new/used", "new/confiscated", "{bankCardId}/edit"})
    public String processForm(
            @Valid SimCard simCard,
            BindingResult result,
            ModelMap model) {
        if (result.hasErrors()) {
            model.put("simCard", simCard);
            return VIEWS_SIM_CARD_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.simCardRepository.save(simCard);
            return "redirect:/breach/{breachId}";
        }
    }

    @GetMapping("{simCardId}/delete")
    public String deleteForm(@PathVariable("simCardId") UUID simCardId) {
        this.simCardRepository.deleteById(simCardId);
        return "redirect:/breach/{breachId}";
    }

    @GetMapping("new/holder/{passport}")
    public ResponseEntity<Person> getPerson(@PathVariable("passport") String passport) {
        return new ResponseEntity<>(personRepository.findTopByPassportOrderByModifiedDesc(passport), HttpStatus.OK);
    }
}
