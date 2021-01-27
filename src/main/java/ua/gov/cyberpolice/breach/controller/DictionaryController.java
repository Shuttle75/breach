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
        model.put("dictionaries", dictionaryRepository.findAllByOrderById());

        return CREATE_OR_UPDATE_DICTIONARY_FORM;
    }

    @GetMapping("breach-type")
    public String processViewBreachTypeForm(Map<String, Object> model) {
        model.put("dictionaries", dictionaryRepository.findAllByOrderById());
        model.put("breachTypes", breachTypeRepository.findAllByOrderById());
        model.put("breachType", new BreachType());

        return CREATE_OR_UPDATE_DICTIONARY_FORM;
    }

    @GetMapping("breach-type/{breachTypeId}")
    public String processEditBreachTypeForm(@PathVariable("breachTypeId") Integer breachTypeId, Map<String, Object> model) {
        model.put("dictionaries", dictionaryRepository.findAllByOrderById());
        model.put("breachTypes", breachTypeRepository.findAllByOrderById());
        model.put("breachType", breachTypeRepository.findById(breachTypeId).orElse(new BreachType()));

        return CREATE_OR_UPDATE_DICTIONARY_FORM;
    }

    @PostMapping("breach-type")
    public String processSaveBreachTypeForm(@Valid BreachType breachType) {
        this.breachTypeRepository.save(breachType);
        return "redirect:/dictionary/breach-type";
    }

    @GetMapping("breach-method")
    public String processViewBreachMethodForm(Map<String, Object> model) {
        model.put("dictionaries", dictionaryRepository.findAllByOrderById());
        model.put("breachMethods", breachMethodRepository.findAllByOrderById());
        model.put("breachMethod", new BreachMethod());

        return CREATE_OR_UPDATE_DICTIONARY_FORM;
    }

    @GetMapping("breach-method/{breachMethodId}")
    public String processEditBreachMethodForm(@PathVariable("breachMethodId") Integer breachMethodId, Map<String, Object> model) {
        model.put("dictionaries", dictionaryRepository.findAllByOrderById());
        model.put("breachMethods", breachMethodRepository.findAllByOrderById());
        model.put("breachMethod", breachMethodRepository.findById(breachMethodId).orElse(new BreachMethod()));

        return CREATE_OR_UPDATE_DICTIONARY_FORM;
    }

    @PostMapping("breach-method")
    public String processSaveBreachMethodForm(@Valid BreachMethod breachMethod) {
        this.breachMethodRepository.save(breachMethod);
        return "redirect:/dictionary/breach-method";
    }

    @GetMapping("participant-type")
    public String processViewParticipantTypeForm(Map<String, Object> model) {
        model.put("dictionaries", dictionaryRepository.findAllByOrderById());
        model.put("participantTypes", participantTypeRepository.findAllByOrderById());
        model.put("participantType", new ParticipantType());

        return CREATE_OR_UPDATE_DICTIONARY_FORM;
    }

    @GetMapping("participant-type/{participantTypeId}")
    public String processEditParticipantTypeForm(@PathVariable("participantTypeId") Integer participantTypeId, Map<String, Object> model) {
        model.put("dictionaries", dictionaryRepository.findAllByOrderById());
        model.put("participantTypes", participantTypeRepository.findAllByOrderById());
        model.put("participantType", participantTypeRepository.findById(participantTypeId).orElse(new ParticipantType()));

        return CREATE_OR_UPDATE_DICTIONARY_FORM;
    }

    @PostMapping("participant-type")
    public String processSaveParticipantTypeForm(@Valid ParticipantType participantType) {
        this.participantTypeRepository.save(participantType);
        return "redirect:/dictionary/participant-type";
    }

    @GetMapping("prison")
    public String processViewPrisonForm(Map<String, Object> model) {
        model.put("dictionaries", dictionaryRepository.findAllByOrderById());
        model.put("prisons", prisonRepository.findAllByOrderById());
        model.put("prison", new Prison());

        return CREATE_OR_UPDATE_DICTIONARY_FORM;
    }

    @GetMapping("prison/{prisonId}")
    public String processEditPrisonForm(@PathVariable("prisonId") Integer prisonId, Map<String, Object> model) {
        model.put("dictionaries", dictionaryRepository.findAllByOrderById());
        model.put("prisons", prisonRepository.findAllByOrderById());
        model.put("prison", prisonRepository.findById(prisonId).orElse(new Prison()));

        return CREATE_OR_UPDATE_DICTIONARY_FORM;
    }

    @PostMapping("prison")
    public String processSavePrisonForm(@Valid Prison prison) {
        this.prisonRepository.save(prison);
        return "redirect:/dictionary/prison";
    }

    @GetMapping("payment-provider")
    public String processViewPaymentProviderForm(Map<String, Object> model) {
        model.put("dictionaries", dictionaryRepository.findAllByOrderById());
        model.put("paymentProviders", paymentProviderRepository.findAllByOrderById());
        model.put("paymentProvider", new PaymentProvider());

        return CREATE_OR_UPDATE_DICTIONARY_FORM;
    }

    @GetMapping("payment-provider/{paymentProviderId}")
    public String processEditPaymentProviderForm(@PathVariable("paymentProviderId") Integer paymentProviderId, Map<String, Object> model) {
        model.put("dictionaries", dictionaryRepository.findAllByOrderById());
        model.put("paymentProviders", paymentProviderRepository.findAllByOrderById());
        model.put("paymentProvider", paymentProviderRepository.findById(paymentProviderId).orElse(new PaymentProvider()));

        return CREATE_OR_UPDATE_DICTIONARY_FORM;
    }

    @PostMapping("payment-provider")
    public String processSavePaymentProviderForm(@Valid PaymentProvider paymentProvider) {
        this.paymentProviderRepository.save(paymentProvider);
        return "redirect:/dictionary/payment-provider";
    }
}
