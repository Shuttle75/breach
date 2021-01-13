package ua.gov.cyberpolice.breach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.gov.cyberpolice.breach.entity.Confiscated;
import ua.gov.cyberpolice.breach.repository.ConfiscatedRepository;
import ua.gov.cyberpolice.breach.repository.RegionRepository;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/breach/{breachId}")
public class ConfiscatedController {
    private static final String VIEWS_CREATE_OR_UPDATE_FORM = "breach/createOrUpdateConfiscatedForm";

    private final ConfiscatedRepository confiscatedRepository;
    private final RegionRepository regionRepository;

    public ConfiscatedController(
            ConfiscatedRepository confiscatedRepository,
            RegionRepository regionRepository) {
        this.confiscatedRepository = confiscatedRepository;
        this.regionRepository = regionRepository;
    }

    @GetMapping("confiscated")
    public String initCreationForm(@PathVariable("breachId") UUID breachId, ModelMap model) {
        Confiscated confiscated = new Confiscated();

        regionRepository.findById("8000000000")
                .ifPresent(confiscated::setRegion);
        confiscated.setHeadId(breachId);

        model.put("confiscated", confiscated);
        return VIEWS_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("confiscated/{confiscatedId}/edit")
    public String initEditForm(@PathVariable("confiscatedId") UUID confiscatedId, ModelMap model) {
        confiscatedRepository.findById(confiscatedId)
                .ifPresent(confiscated -> model.put("confiscated", confiscated));
        return VIEWS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping({"confiscated", "confiscated/{confiscatedId}/edit"})
    public String processForm(@Valid Confiscated confiscated, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.put("confiscated", confiscated);
            return VIEWS_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.confiscatedRepository.save(confiscated);
            return "redirect:/breach/{breachId}";
        }
    }

    @GetMapping("confiscated/{confiscatedId}/delete")
    public String deleteForm(@PathVariable("confiscatedId") UUID confiscatedId) {
        this.confiscatedRepository.deleteById(confiscatedId);
        return "redirect:/breach/{breachId}";
    }
}
