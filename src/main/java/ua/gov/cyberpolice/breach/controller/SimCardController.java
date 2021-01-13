package ua.gov.cyberpolice.breach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.gov.cyberpolice.breach.entity.Person;
import ua.gov.cyberpolice.breach.entity.SimCard;
import ua.gov.cyberpolice.breach.repository.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/breach/{breachId}")
public class SimCardController {
    private static final String VIEWS_SIM_CARD_CREATE_OR_UPDATE_FORM = "breach/createOrUpdateSimCardForm";

    private final SimCardRepository simCardRepository;
    private final RegionRepository regionRepository;

    public SimCardController(
            SimCardRepository simCardRepository,
            RegionRepository regionRepository) {
        this.simCardRepository = simCardRepository;
        this.regionRepository = regionRepository;
    }

    @GetMapping("confiscated/{confiscatedId}/sim-card")
    public String initCreationForm(@PathVariable("confiscatedId") UUID confiscatedId, ModelMap model) {
        SimCard simCard = new SimCard();
        Person person = new Person();

        regionRepository.findById("8000000000")
                .ifPresent(person::setRegion);
        simCard.setHeadId(confiscatedId);

        model.put("simCard", simCard);
        return VIEWS_SIM_CARD_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("sim-card/{simCardId}/edit")
    public String initEditForm(@PathVariable("simCardId") UUID simCardId, ModelMap model) {
        simCardRepository.findById(simCardId)
                .ifPresent(simCard -> model.put("simCard", simCard));
        return VIEWS_SIM_CARD_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping({"confiscated/{confiscatedId}/sim-card", "sim-card/{simCardId}/edit"})
    public String processForm(
            @Valid SimCard simCard,
            BindingResult result,
            ModelMap model) {
        if (result.hasErrors()) {
            model.put("simCard", simCard);
            return VIEWS_SIM_CARD_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.simCardRepository.save(simCard);
            return "redirect:/breach/{breachId}";
        }
    }

    @GetMapping("sim-card/{simCardId}/delete")
    public String deleteForm(@PathVariable("simCardId") UUID simCardId) {
        this.simCardRepository.deleteById(simCardId);
        return "redirect:/breach/{breachId}";
    }
}
