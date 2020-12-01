package ua.gov.cyberpolice.breach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.gov.cyberpolice.breach.entity.*;
import ua.gov.cyberpolice.breach.repository.BankCardRepository;
import ua.gov.cyberpolice.breach.repository.BreachRepository;
import ua.gov.cyberpolice.breach.repository.RegionRepository;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/breach/{breachId}")
public class ParticipantController {
    private static final String VIEWS_PARTICIPANT_CREATE_OR_UPDATE_FORM = "breach/createOrUpdateParticipantForm";

    private BreachRepository breachRepository;
    private BankCardRepository bankCardRepository;
    private RegionRepository regionRepository;
    private Iterable<Region> regions;

    public ParticipantController(
            BreachRepository breachRepository,
            BankCardRepository bankCardRepository,
            RegionRepository regionRepository) {
        this.bankCardRepository = bankCardRepository;
        this.breachRepository = breachRepository;
        this.regionRepository = regionRepository;
        this.regions = regionRepository.findAll();
    }

    @ModelAttribute("breach")
    public Breach findBreach(@PathVariable("breachId") UUID breachId) {
        return this.breachRepository.findById(breachId).get();
    }

    @GetMapping("/participant/new")
    public String initCreationForm(Breach breach, ModelMap model) {
        Participant participant = new Participant();
        Person person = new Person();

        participant.setBreachId(breach.getId());
        regionRepository.findById("8000000000")
                .ifPresent(person::setRegion);

        model.put("participant", participant);
        model.put("regions", this.regions);
        return VIEWS_PARTICIPANT_CREATE_OR_UPDATE_FORM;
    }

//    @GetMapping("/bank-card/{bankCardId}/edit")
//    public String initEditForm(@PathVariable("bankCardId") UUID bankCardId, ModelMap model) {
//        BankCard bankCard = bankCardRepository.findById(bankCardId).get();
//
//        model.put("bankCard", bankCard);
//        model.put("regions", this.regions);
//        return VIEWS_BANK_CARD_CREATE_OR_UPDATE_FORM;
//    }

    @PostMapping({"/participant/new", "/participant/{bankCardId}/edit"})
    public String processForm(
            @Valid BankCard bankCard,
            BindingResult result,
            ModelMap model) {
        if (result.hasErrors()) {
            model.put("bankCard", bankCard);
            model.put("regions", this.regions);
            return VIEWS_PARTICIPANT_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.bankCardRepository.save(bankCard);
            return "redirect:/breach/{breachId}";
        }
    }

    @PostMapping("/participant/{bankCardId}/delete")
    public String deleteForm(@PathVariable("bankCardId") UUID bankCardId) {
        this.bankCardRepository.deleteById(bankCardId);
        return "redirect:/breach/{breachId}";
    }
}
