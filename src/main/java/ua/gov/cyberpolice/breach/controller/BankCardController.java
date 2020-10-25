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
    public Breach findOwner(@PathVariable("breachId") UUID breachId) {
        return this.breachRepository.findById(breachId).get();
    }

    @GetMapping("/bank-card/new")
    public String initCreationForm(Breach breach, ModelMap model) {
        Person person = new Person();

        regionRepository.findById("8000000000")
                .ifPresent(person::setRegion);

        BankCard bankCard = new BankCard();
        bankCard.setUserId(breach.getId());

        model.put("bankCard", bankCard);
        model.put("holder", person);
        model.put("regions", this.regions);
        return VIEWS_BANK_CARD_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/bank-card/new")
    public String processCreationForm(
            @Valid BankCard bankCard,
            @Valid Person holder,
            BindingResult result,
            ModelMap model) {
        if (result.hasErrors()) {
            model.put("bankCard", bankCard);
            model.put("holder", holder);
            return VIEWS_BANK_CARD_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.personRepository.save(holder);
            bankCard.setHolder(holder);
            this.bankCardRepository.save(bankCard);
            return "redirect:/breach/{breachId}";
        }
    }

}
