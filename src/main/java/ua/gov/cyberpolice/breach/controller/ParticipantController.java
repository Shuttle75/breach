package ua.gov.cyberpolice.breach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.gov.cyberpolice.breach.entity.*;
import ua.gov.cyberpolice.breach.repository.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("breach/{breachId}")
public class ParticipantController {
    private static final String VIEWS_PARTICIPANT_CREATE_OR_UPDATE_FORM = "breach/createOrUpdateParticipantForm";

    private final ParticipantRepository participantRepository;
    private final RegionRepository regionRepository;

    public ParticipantController(
            ParticipantRepository participantRepository,
            RegionRepository regionRepository) {
        this.participantRepository = participantRepository;
        this.regionRepository = regionRepository;
    }

    @GetMapping("participant")
    public String initCreationForm(@PathVariable("breachId") UUID breachId, ModelMap model) {
        Participant participant = new Participant();
        Person person = new Person();

        participant.setHeadId(breachId);
        participant.setParticipantType(new ParticipantType());
        participant.getParticipantType().setId(1);
        regionRepository.findById("8000000000")
                .ifPresent(person::setRegion);

        model.put("participant", participant);
        return VIEWS_PARTICIPANT_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("participant/{participantId}/edit")
    public String initEditForm(@PathVariable("participantId") UUID participantId, ModelMap model) {
        participantRepository.findById(participantId)
                .ifPresent(participant -> model.put("participant", participant));
        return VIEWS_PARTICIPANT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping({"participant", "participant/{participantId}/edit"})
    public String processForm(
            @Valid Participant participant,
            @RequestParam("file") MultipartFile multipartFile,
            BindingResult result,
            ModelMap model) throws IOException {
        if (result.hasErrors()) {
            model.put("participant", participant);
            return VIEWS_PARTICIPANT_CREATE_OR_UPDATE_FORM;
        }
        else {
            participant.getPerson().setImage(multipartFile.getBytes());
            this.participantRepository.save(participant);
            return "redirect:/breach/{breachId}";
        }
    }

    @GetMapping("participant/{participantId}/delete")
    public String deleteForm(@PathVariable("participantId") UUID participantId) {
        this.participantRepository.deleteById(participantId);
        return "redirect:/breach/{breachId}";
    }

    @GetMapping("participant/{participantId}/imageDisplay")
    public void getImageAsByteArray(
            @PathVariable("participantId") UUID participantId,
            HttpServletResponse response) throws IOException {

        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(participantRepository.findById(participantId).get().getPerson().getImage());
        response.getOutputStream().close();
    }
}
