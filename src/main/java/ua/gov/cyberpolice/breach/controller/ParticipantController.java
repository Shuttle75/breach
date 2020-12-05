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
@RequestMapping("/breach/{breachId}")
public class ParticipantController {
    private static final String VIEWS_PARTICIPANT_CREATE_OR_UPDATE_FORM = "breach/createOrUpdateParticipantForm";

    private BreachRepository breachRepository;
    private ParticipantRepository participantRepository;
    private RegionRepository regionRepository;
    private Iterable<Region> regions;
    private Iterable<ParticipantType> participantTypes;

    public ParticipantController(
            BreachRepository breachRepository,
            ParticipantRepository participantRepository,
            RegionRepository regionRepository,
            ParticipantTypeRepository participantTypeRepository) {
        this.participantRepository = participantRepository;
        this.breachRepository = breachRepository;
        this.regionRepository = regionRepository;
        this.regions = regionRepository.findAll();
        this.participantTypes = participantTypeRepository.findAll();
    }

    @ModelAttribute("breach")
    public Breach findBreach(@PathVariable("breachId") UUID breachId) {
        return this.breachRepository.findById(breachId)
                .orElseThrow(() -> new RuntimeException("Not found breachId " + breachId));
    }

    @GetMapping("/participant/new")
    public String initCreationForm(Breach breach, ModelMap model) {
        Participant participant = new Participant();
        Person person = new Person();

        participant.setBreachId(breach.getId());
        participant.setParticipantType(new ParticipantType());
        participant.getParticipantType().setId(1);
        regionRepository.findById("8000000000")
                .ifPresent(person::setRegion);

        model.put("participant", participant);
        model.put("regions", this.regions);
        model.put("participantTypes", this.participantTypes);
        return VIEWS_PARTICIPANT_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("/participant/{participantId}/edit")
    public String initEditForm(@PathVariable("participantId") UUID participantId, ModelMap model) {
        participantRepository.findById(participantId)
                .ifPresent(participant -> {
                    model.put("participant", participant);
                    model.put("regions", this.regions);
                    model.put("participantTypes", this.participantTypes);
                });
        return VIEWS_PARTICIPANT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping({"/participant/new", "/participant/{participantId}/edit"})
    public String processForm(
            @Valid Participant participant,
            BindingResult result,
            ModelMap model) {
        if (result.hasErrors()) {
            model.put("participant", participant);
            model.put("regions", this.regions);
            return VIEWS_PARTICIPANT_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.participantRepository.save(participant);
            return "redirect:/breach/{breachId}";
        }
    }

    @PostMapping("/participant/{participantId}/delete")
    public String deleteForm(@PathVariable("participantId") UUID participantId) {
        this.participantRepository.deleteById(participantId);
        return "redirect:/breach/{breachId}";
    }
}
