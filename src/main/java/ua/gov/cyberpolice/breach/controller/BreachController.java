package ua.gov.cyberpolice.breach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.gov.cyberpolice.breach.entity.Breach;
import ua.gov.cyberpolice.breach.repository.BreachRepository;
import ua.gov.cyberpolice.breach.repository.MethodRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class BreachController {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "breaches/createOrUpdateBreachForm";

    private final BreachRepository breachRepository;
    private final MethodRepository methodRepository;

    public BreachController(BreachRepository breachRepository, MethodRepository methodRepository) {
        this.breachRepository = breachRepository;
        this.methodRepository = methodRepository;
    }

    @GetMapping("/breaches/new")
    public String initCreationForm(Map<String, Object> model) {
        model.put("breach", new Breach());
        model.put("methods", methodRepository.findAll());

        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/breaches/new")
    public String processCreationForm(@Valid Breach breach, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.breachRepository.save(breach);
            return "redirect:/breaches";
        }
    }

    @GetMapping("/breaches/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("breach", new Breach());
        return "breaches/findBreaches";
    }

    @GetMapping("/breaches")
    public String processFindForm(Breach breach, BindingResult result, Map<String, Object> model) {

//        // allow parameterless GET request for /owners to return all records
//        if (owner.getLastName() == null) {
//            owner.setLastName(""); // empty string signifies broadest possible search
//        }

        // find owners by last name
        List<Breach> results = this.breachRepository.findAll();

        model.put("selections", results);
        return "breaches/breachesList";
    }

    @GetMapping("/breaches/{breachId}")
    public ModelAndView showOwner(@PathVariable("breachId") UUID breachId) {
        ModelAndView modelAndView = new ModelAndView(VIEWS_OWNER_CREATE_OR_UPDATE_FORM);

        Breach breach = this.breachRepository.findById(breachId).get();
        modelAndView.addObject(breach);
        return modelAndView;
    }
}
