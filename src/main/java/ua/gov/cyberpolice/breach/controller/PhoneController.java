package ua.gov.cyberpolice.breach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.gov.cyberpolice.breach.entity.*;
import ua.gov.cyberpolice.breach.repository.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("breach/{breachId}")
public class PhoneController {
    private static final String VIEWS_PHONE_CREATE_OR_UPDATE_FORM = "breach/createOrUpdatePhoneForm";

    private final PhoneRepository phoneRepository;
    private final RegionRepository regionRepository;

    public PhoneController(
            PhoneRepository phoneRepository,
            RegionRepository regionRepository) {
        this.phoneRepository = phoneRepository;
        this.regionRepository = regionRepository;
    }

    @GetMapping("confiscated/{confiscatedId}/phone")
    public String initCreationForm(@PathVariable("confiscatedId") UUID confiscatedId, ModelMap model) {
        Phone phone = new Phone();
        Person person = new Person();

        regionRepository.findById("8000000000")
                .ifPresent(person::setRegion);
        phone.setHeadId(confiscatedId);
        phone.setHolder(person);

        model.put("phone", phone);
        return VIEWS_PHONE_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("phone/{phoneId}/edit")
    public String initEditForm(@PathVariable("phoneId") UUID phoneId, ModelMap model) {
        phoneRepository.findById(phoneId)
                .ifPresent(phone -> model.put("phone", phone));
        return VIEWS_PHONE_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping({"confiscated/{confiscatedId}/phone", "phone/{phoneId}/edit"})
    public String processForm(@Valid Phone phone, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.put("phone", phone);
            return VIEWS_PHONE_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.phoneRepository.save(phone);
            return "redirect:/breach/{breachId}";
        }
    }

    @GetMapping("phone/{phoneId}/delete")
    public String deleteForm(@PathVariable("phoneId") UUID phoneId) {
        this.phoneRepository.deleteById(phoneId);
        return "redirect:/breach/{breachId}";
    }
}
