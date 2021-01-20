package ua.gov.cyberpolice.breach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.gov.cyberpolice.breach.entity.BreachMethod;
import ua.gov.cyberpolice.breach.entity.Dictionary;
import ua.gov.cyberpolice.breach.entity.ParticipantType;
import ua.gov.cyberpolice.breach.entity.PaymentProvider;
import ua.gov.cyberpolice.breach.repository.DictionaryRepository;

import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("dictionary")
public class DictionaryController {

    private static final String VIEWS_BREACH_DICTIONARY_FORM = "breach/createOrUpdateDictionaryForm";

    private final DictionaryRepository dictionaryRepository;

    public DictionaryController(
            DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
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
        Iterable<BreachMethod> breachMethodList;
        Iterable<ParticipantType> participantTypeList;
//        Iterable<Prison> prisonList;
        Iterable<PaymentProvider> paymentProvider;

        switch (dictionaryType) {
            case "breach-method":
                break;
            case "participant-type":
                break;
            case "prison":
                break;
            case "payment-provider":
                break;
        }




        model.put("dictionaries", dictionaries);
        return VIEWS_BREACH_DICTIONARY_FORM;
    }
}
