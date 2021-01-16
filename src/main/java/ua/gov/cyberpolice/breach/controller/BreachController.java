package ua.gov.cyberpolice.breach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.gov.cyberpolice.breach.entity.Breach;
import ua.gov.cyberpolice.breach.repository.BreachRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Controller
public class BreachController {

    private static final String VIEWS_BREACH_CREATE_OR_UPDATE_FORM = "breach/createOrUpdateBreachForm";

    private final BreachRepository breachRepository;

    public BreachController(
            BreachRepository breachRepository) {
        this.breachRepository = breachRepository;
    }

    @GetMapping("/breach/new")
    public String initCreationForm(Map<String, Object> model) {
        Breach breach = new Breach();
        breach.setParticipants(new ArrayList<>());
        breach.setPaymentMoves(new ArrayList<>());
        breach.setCallDataRecords(new ArrayList<>());
        breach.setConfiscated(new ArrayList<>());

        model.put("breach", breach);
        return VIEWS_BREACH_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/breach/new")
    public String processCreationForm(@Valid Breach breach, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return VIEWS_BREACH_CREATE_OR_UPDATE_FORM;
        }
        else {
            Breach savedBreach = this.breachRepository.save(breach);
            redirectAttributes.addAttribute("breachId", savedBreach.getId());
            return "redirect:/breach/{breachId}";
        }
    }

    @GetMapping("/breach/{breachId}")
    public ModelAndView initEditForm(@PathVariable("breachId") UUID breachId) {
        ModelAndView modelAndView = new ModelAndView(VIEWS_BREACH_CREATE_OR_UPDATE_FORM);

        this.breachRepository.findById(breachId)
                .ifPresent(breach -> modelAndView.addObject("breach", breach));
        return modelAndView;
    }

    @PostMapping("/breach/{breachId}")
    public String postEditForm(@PathVariable("breachId") UUID breachId, @Valid Breach breach, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_BREACH_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.breachRepository.save(breach);
            return "redirect:/breach/find";
        }
    }
}
