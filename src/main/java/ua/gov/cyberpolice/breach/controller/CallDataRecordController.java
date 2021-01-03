package ua.gov.cyberpolice.breach.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.gov.cyberpolice.breach.entity.Breach;
import ua.gov.cyberpolice.breach.entity.CallDataRecord;
import ua.gov.cyberpolice.breach.entity.Person;
import ua.gov.cyberpolice.breach.repository.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/breach/{breachId}/call-data-record")
public class CallDataRecordController {
    private static final String VIEWS_CREATE_OR_UPDATE_FORM = "breach/createOrUpdateCallDataRecordForm";

    private final BreachRepository breachRepository;
    private final CallDataRecordRepository callDataRecordRepository;
    private final RegionRepository regionRepository;
    private final PersonRepository personRepository;

    public CallDataRecordController(
            BreachRepository breachRepository,
            CallDataRecordRepository callDataRecordRepository,
            RegionRepository regionRepository,
            PersonRepository personRepository) {
        this.callDataRecordRepository = callDataRecordRepository;
        this.breachRepository = breachRepository;
        this.regionRepository = regionRepository;
        this.personRepository = personRepository;
    }

    @ModelAttribute("breach")
    public Breach findBreach(@PathVariable("breachId") UUID breachId) {
        return this.breachRepository.findById(breachId)
                .orElseThrow(() -> new RuntimeException("Not found breachId " + breachId));
    }

    @GetMapping("new")
    public String initCreationForm(Breach breach, ModelMap model) {
        CallDataRecord callDataRecord = new CallDataRecord();
        Person person = new Person();

        regionRepository.findById("8000000000")
                .ifPresent(person::setRegion);
            callDataRecord.setBreachId(breach.getId());

        model.put("callDataRecord", callDataRecord);
        return VIEWS_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("{callDataRecordId}/edit")
    public String initEditForm(@PathVariable("callDataRecordId") UUID callDataRecordId, ModelMap model) {
        callDataRecordRepository.findById(callDataRecordId)
                .ifPresent(callDataRecord -> model.put("callDataRecord", callDataRecord));
        return VIEWS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping({"new", "{callDataRecordId}/edit"})
    public String processForm(
            @Valid CallDataRecord callDataRecord,
            BindingResult result,
            ModelMap model) {
        if (result.hasErrors()) {
            model.put("callDataRecord", callDataRecord);
            return VIEWS_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.callDataRecordRepository.save(callDataRecord);
            return "redirect:/breach/{breachId}";
        }
    }

    @GetMapping("{callDataRecordId}/delete")
    public String deleteForm(@PathVariable("callDataRecordId") UUID callDataRecordId) {
        this.callDataRecordRepository.deleteById(callDataRecordId);
        return "redirect:/breach/{breachId}";
    }

    @GetMapping("new/holder/{passport}")
    public ResponseEntity<Person> getPerson(@PathVariable("passport") String passport) {
        return new ResponseEntity<>(personRepository.findTopByPassportOrderByModifiedDesc(passport), HttpStatus.OK);
    }
}
