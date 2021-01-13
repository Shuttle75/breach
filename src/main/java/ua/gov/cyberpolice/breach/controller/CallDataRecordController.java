package ua.gov.cyberpolice.breach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.gov.cyberpolice.breach.entity.CallDataRecord;
import ua.gov.cyberpolice.breach.entity.Person;
import ua.gov.cyberpolice.breach.repository.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("breach/{breachId}")
public class CallDataRecordController {
    private static final String VIEWS_CREATE_OR_UPDATE_FORM = "breach/createOrUpdateCallDataRecordForm";

    private final CallDataRecordRepository callDataRecordRepository;
    private final RegionRepository regionRepository;

    public CallDataRecordController(
            CallDataRecordRepository callDataRecordRepository,
            RegionRepository regionRepository) {
        this.callDataRecordRepository = callDataRecordRepository;
        this.regionRepository = regionRepository;
    }

    @GetMapping("call-data-record")
    public String initCreationForm(@PathVariable("breachId") UUID breachId, ModelMap model) {
        CallDataRecord callDataRecord = new CallDataRecord();
        Person person = new Person();

        regionRepository.findById("8000000000")
                .ifPresent(person::setRegion);
        callDataRecord.setHeadId(breachId);

        model.put("callDataRecord", callDataRecord);
        return VIEWS_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("call-data-record/{callDataRecordId}/edit")
    public String initEditForm(@PathVariable("callDataRecordId") UUID callDataRecordId, ModelMap model) {
        callDataRecordRepository.findById(callDataRecordId)
                .ifPresent(callDataRecord -> model.put("callDataRecord", callDataRecord));
        return VIEWS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping({"call-data-record", "call-data-record/{callDataRecordId}/edit"})
    public String processForm(@Valid CallDataRecord callDataRecord, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.put("callDataRecord", callDataRecord);
            return VIEWS_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.callDataRecordRepository.save(callDataRecord);
            return "redirect:/breach/{breachId}";
        }
    }

    @GetMapping("call-data-record/{callDataRecordId}/delete")
    public String deleteForm(@PathVariable("callDataRecordId") UUID callDataRecordId) {
        this.callDataRecordRepository.deleteById(callDataRecordId);
        return "redirect:/breach/{breachId}";
    }
}
