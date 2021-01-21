package ua.gov.cyberpolice.breach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.gov.cyberpolice.breach.entity.*;
import ua.gov.cyberpolice.breach.repository.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("dictionary")
public class DictionaryController {

    private static final String VIEWS_BREACH_DICTIONARY_FORM = "breach/createOrUpdateDictionaryForm";

    private final DictionaryRepository dictionaryRepository;
    private final BreachTypeRepository breachTypeRepository;
    private final BreachMethodRepository breachMethodRepository;
    private final ParticipantTypeRepository participantTypeRepository;
    private final PrisonRepository prisonRepository;
    private final PaymentProviderRepository paymentProviderRepository;

    public DictionaryController(
            DictionaryRepository dictionaryRepository,
            BreachTypeRepository breachTypeRepository,
            BreachMethodRepository breachMethodRepository,
            ParticipantTypeRepository participantTypeRepository,
            PrisonRepository prisonRepository,
            PaymentProviderRepository paymentProviderRepository
    ) {
        this.dictionaryRepository = dictionaryRepository;
        this.breachTypeRepository = breachTypeRepository;
        this.breachMethodRepository = breachMethodRepository;
        this.participantTypeRepository = participantTypeRepository;
        this.prisonRepository = prisonRepository;
        this.paymentProviderRepository = paymentProviderRepository;
    }

    @GetMapping
    public String processListDictionaryForm(Map<String, Object> model) {
        Iterable<Dictionary> dictionaries = dictionaryRepository.findAll();

        model.put("dictionaries", dictionaries);
        return VIEWS_BREACH_DICTIONARY_FORM;
    }

    @GetMapping("{dictionaryType}")
    public String processEditDictionaryForm(
            @PathVariable("dictionaryType") String dictionaryType,
            Map<String, Object> model) {
        Iterable<Dictionary> dictionaries = dictionaryRepository.findAll();
        Iterable<BreachType> breachTypes = null;
        Iterable<BreachMethod> breachMethods = null;
        Iterable<ParticipantType> participantTypes = null;
        Iterable<Prison> prisons = null;
        Iterable<PaymentProvider> paymentProviders = null;

        switch (dictionaryType) {
            case "breach-type":
                breachTypes = breachTypeRepository.findAll();
                break;
            case "breach-method":
                breachMethods = breachMethodRepository.findAll();
                break;
            case "participant-type":
                participantTypes = participantTypeRepository.findAll();
                break;
            case "prison":
                prisons = prisonRepository.findAll();
                break;
            case "payment-provider":
                paymentProviders = paymentProviderRepository.findAll();
                break;
        }

        model.put("dictionaries", dictionaries);
        model.put("breachTypes", breachTypes);
        model.put("breachMethods", breachMethods);
        model.put("participantTypes", participantTypes);
        model.put("prisons", prisons);
        model.put("paymentProviders", paymentProviders);
        return VIEWS_BREACH_DICTIONARY_FORM;
    }
}
