package ua.gov.cyberpolice.breach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.gov.cyberpolice.breach.entity.Person;
import ua.gov.cyberpolice.breach.entity.WebsitePayment;
import ua.gov.cyberpolice.breach.repository.RegionRepository;
import ua.gov.cyberpolice.breach.repository.WebsiteRepository;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/breach/{breachId}")
public class WebsiteController {
    private static final String VIEWS_CREATE_OR_UPDATE_FORM = "breach/createOrUpdateWebsiteForm";

    private final WebsiteRepository websiteRepository;
    private final RegionRepository regionRepository;

    public WebsiteController(
            WebsiteRepository websiteRepository,
            RegionRepository regionRepository) {
        this.websiteRepository = websiteRepository;
        this.regionRepository = regionRepository;
    }

    @GetMapping("website")
    public String initCreationForm(@PathVariable("breachId") UUID breachId, ModelMap model) {
        WebsitePayment website = new WebsitePayment();
        Person person = new Person();

        regionRepository.findById("8000000000")
                .ifPresent(person::setRegion);
        website.setHeadId(breachId);

        model.put("website", website);
        return VIEWS_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("website/{websiteId}/edit")
    public String initEditForm(@PathVariable("websiteId") UUID websiteId, ModelMap model) {
        websiteRepository.findById(websiteId)
                .ifPresent(website -> model.put("website", website));
        return VIEWS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping({"website", "website/{websiteId}/edit"})
    public String processForm(
            @Valid WebsitePayment website,
            BindingResult result,
            ModelMap model) {
        if (result.hasErrors()) {
            model.put("website", website);
            return VIEWS_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.websiteRepository.save(website);
            return "redirect:/breach/{breachId}";
        }
    }

    @GetMapping("website/{websiteId}/delete")
    public String deleteForm(@PathVariable("websiteId") UUID websiteId) {
        this.websiteRepository.deleteById(websiteId);
        return "redirect:/breach/{breachId}";
    }
}
