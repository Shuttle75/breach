package ua.gov.cyberpolice.breach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.gov.cyberpolice.breach.entity.BankCard;
import ua.gov.cyberpolice.breach.entity.Breach;
import ua.gov.cyberpolice.breach.entity.Person;
import ua.gov.cyberpolice.breach.entity.Region;
import ua.gov.cyberpolice.breach.repository.BankCardRepository;
import ua.gov.cyberpolice.breach.repository.BreachRepository;
import ua.gov.cyberpolice.breach.repository.PersonRepository;
import ua.gov.cyberpolice.breach.repository.RegionRepository;

import javax.validation.Valid;
import javax.xml.ws.Holder;
import java.util.UUID;

@Controller
@RequestMapping("/breach/{breachId}")
public class BankCardController {
    private static final String VIEWS_BANK_CARD_CREATE_OR_UPDATE_FORM = "breach/createOrUpdateBankCardForm";

    private BreachRepository breachRepository;
    private BankCardRepository bankCardRepository;
    private PersonRepository personRepository;
    private RegionRepository regionRepository;
    private Iterable<Region> regions;

    public BankCardController(
            BreachRepository breachRepository,
            BankCardRepository bankCardRepository,
            PersonRepository personRepository,
            RegionRepository regionRepository) {
        this.bankCardRepository = bankCardRepository;
        this.breachRepository = breachRepository;
        this.personRepository = personRepository;
        this.regionRepository = regionRepository;
        this.regions = regionRepository.findAll();
    }

    @ModelAttribute("breach")
    public Breach findBreach(@PathVariable("breachId") UUID breachId) {
        return this.breachRepository.findById(breachId).get();
    }

//    @ModelAttribute("bank-card")
//    public BankCard findOwner(@PathVariable("bankCardId") UUID bankCardId) {
//        return this.bankCardRepository.findById(bankCardId).get();
//    }

    @GetMapping("/bank-card/new")
    public String initCreationForm(Breach breach, ModelMap model) {
        BankCard bankCard = new BankCard();
        Person person = new Person();

        regionRepository.findById("8000000000")
                .ifPresent(person::setRegion);
        bankCard.setUserId(breach.getId());

        model.put("bankCard", bankCard);
        model.put("regions", this.regions);
        return VIEWS_BANK_CARD_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("/bank-card/{bankCardId}/edit")
    public String initEditForm(@PathVariable("bankCardId") UUID bankCardId, ModelMap model) {
        BankCard bankCard = bankCardRepository.findById(bankCardId).get();

        model.put("bankCard", bankCard);
        model.put("regions", this.regions);
        return VIEWS_BANK_CARD_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping({"/bank-card/new", "/bank-card/{bankCardId}/edit"})
    public String processForm(
            @Valid BankCard bankCard,
            BindingResult result,
            ModelMap model) {
        if (result.hasErrors()) {
            model.put("bankCard", bankCard);
            model.put("regions", this.regions);
            return VIEWS_BANK_CARD_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.bankCardRepository.save(bankCard);
            return "redirect:/breach/{breachId}";
        }
    }

    @PostMapping("/bank-card/{bankCardId}/delete")
    public String deleteForm(@PathVariable("bankCardId") UUID bankCardId) {
        this.bankCardRepository.deleteById(bankCardId);
        return "redirect:/breach/{breachId}";
    }
}
