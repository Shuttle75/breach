package ua.gov.cyberpolice.breach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.gov.cyberpolice.breach.entity.BankCard;
import ua.gov.cyberpolice.breach.entity.Person;
import ua.gov.cyberpolice.breach.repository.BankCardRepository;
import ua.gov.cyberpolice.breach.repository.RegionRepository;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/breach/{breachId}")
public class BankCardController {
    private static final String VIEWS_BANK_CARD_CREATE_OR_UPDATE_FORM = "breach/createOrUpdateBankCardForm";

    private final BankCardRepository bankCardRepository;
    private final RegionRepository regionRepository;

    public BankCardController(
            BankCardRepository bankCardRepository,
            RegionRepository regionRepository) {
        this.bankCardRepository = bankCardRepository;
        this.regionRepository = regionRepository;
    }

    @GetMapping({"bank-card", "confiscated/{confiscatedId}/bank-card"})
    public String initCreationForm(
            @PathVariable("breachId") UUID breachId,
            @PathVariable(value = "confiscatedId",required = false) UUID confiscatedId,
            ModelMap model) {
        BankCard bankCard = new BankCard();
        Person person = new Person();

        regionRepository.findById("8000000000")
                .ifPresent(person::setRegion);
        if(confiscatedId == null) {
            bankCard.setHeadId(breachId);
        } else {
            bankCard.setHeadId(confiscatedId);
        }

        model.put("bankCard", bankCard);
        return VIEWS_BANK_CARD_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("/bank-card/{bankCardId}/edit")
    public String initEditForm(@PathVariable("bankCardId") UUID bankCardId, ModelMap model) {
        bankCardRepository.findById(bankCardId)
                .ifPresent(bankCard -> model.put("bankCard", bankCard));
        return VIEWS_BANK_CARD_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping({"bank-card", "confiscated/{confiscatedId}/bank-card", "/bank-card/{bankCardId}/edit"})
    public String processForm(@Valid BankCard bankCard, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.put("bankCard", bankCard);
            return VIEWS_BANK_CARD_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.bankCardRepository.save(bankCard);
            return "redirect:/breach/{breachId}";
        }
    }

    @GetMapping("bank-card/{bankCardId}/delete")
    public String deleteForm(@PathVariable("bankCardId") UUID bankCardId) {
        this.bankCardRepository.deleteById(bankCardId);
        return "redirect:/breach/{breachId}";
    }
}
