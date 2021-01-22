package ua.gov.cyberpolice.breach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.gov.cyberpolice.breach.entity.*;
import ua.gov.cyberpolice.breach.repository.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("dictionary")
public class DictionaryController {

    private static final String CREATE_OR_UPDATE_DICTIONARY_FORM = "breach/createOrUpdateDictionaryForm";

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
    public String processViewDictionaryForm(Map<String, Object> model) {
        model.put("dictionaries", dictionaryRepository.findAll());

        return CREATE_OR_UPDATE_DICTIONARY_FORM;
    }

    @GetMapping("breach-type")
    public String processViewBreachTypeForm(Map<String, Object> model) {
        model.put("dictionaries", dictionaryRepository.findAll());
        model.put("breachTypes", breachTypeRepository.findAll());
        model.put("breachType", new BreachType());

        return CREATE_OR_UPDATE_DICTIONARY_FORM;
    }

    @GetMapping("breach-type/{breachTypeId}")
    public String processEditBreachTypeForm(@PathVariable("breachTypeId") Integer breachTypeId, Map<String, Object> model) {
        model.put("dictionaries", dictionaryRepository.findAll());
        model.put("breachTypes", breachTypeRepository.findAll());
        model.put("breachType", breachTypeRepository.findById(breachTypeId).orElse(new BreachType()));

        return CREATE_OR_UPDATE_DICTIONARY_FORM;
    }

    @GetMapping("breach-method")
    public String processViewBreachMethodForm(Map<String, Object> model) {
        model.put("dictionaries", dictionaryRepository.findAll());
        model.put("breachMethods", breachMethodRepository.findAll());
        model.put("breachMethod", new BreachMethod());

        return CREATE_OR_UPDATE_DICTIONARY_FORM;
    }

    @GetMapping("participant-type")
    public String processViewParticipantTypeForm(Map<String, Object> model) {
        model.put("dictionaries", dictionaryRepository.findAll());
        model.put("participantTypes", participantTypeRepository.findAll());
        model.put("participantType", new ParticipantType());

        return CREATE_OR_UPDATE_DICTIONARY_FORM;
    }

    @GetMapping("prison")
    public String processViewPrisonForm(Map<String, Object> model) {
        model.put("dictionaries", dictionaryRepository.findAll());
        model.put("prisons", prisonRepository.findAll());
        model.put("prison", new Prison());

        return CREATE_OR_UPDATE_DICTIONARY_FORM;
    }

    @GetMapping("payment-provider")
    public String processViewPaymentProviderForm(Map<String, Object> model) {
        model.put("dictionaries", dictionaryRepository.findAll());
        model.put("paymentProviders", paymentProviderRepository.findAll());
        model.put("paymentProvider", new PaymentProvider());

        return CREATE_OR_UPDATE_DICTIONARY_FORM;
    }

    @PostMapping("breach-type")
    public String processForm(@Valid BreachType breachType) {
        this.breachTypeRepository.save(breachType);
        return "redirect:/dictionary/breach-type";
    }
}
