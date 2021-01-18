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
import ua.gov.cyberpolice.breach.repository.WebsitePaymentRepository;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/breach/{breachId}")
public class WebsitePaymentController {
    private static final String VIEWS_CREATE_OR_UPDATE_FORM = "breach/createOrUpdateWebsitePaymentForm";

    private final WebsitePaymentRepository websitePaymentRepository;
    private final RegionRepository regionRepository;

    public WebsitePaymentController(
            WebsitePaymentRepository websitePaymentRepository,
            RegionRepository regionRepository) {
        this.websitePaymentRepository = websitePaymentRepository;
        this.regionRepository = regionRepository;
    }

    @GetMapping("website-payment")
    public String initCreationForm(@PathVariable("breachId") UUID breachId, ModelMap model) {
        WebsitePayment payment = new WebsitePayment();
        Person person = new Person();

        regionRepository.findById("8000000000")
                .ifPresent(person::setRegion);
        payment.setHeadId(breachId);

        model.put("payment", payment);
        return VIEWS_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("website-payment/{paymentId}/edit")
    public String initEditForm(@PathVariable("paymentId") UUID paymentId, ModelMap model) {
        this.websitePaymentRepository.findById(paymentId)
                .ifPresent(payment -> model.put("payment", payment));
        return VIEWS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping({"website-payment", "website-payment/{paymentId}/edit"})
    public String processForm(
            @Valid WebsitePayment website,
            BindingResult result,
            ModelMap model) {
        if (result.hasErrors()) {
            model.put("website", website);
            return VIEWS_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.websitePaymentRepository.save(website);
            return "redirect:/breach/{breachId}";
        }
    }

    @GetMapping("website-payment/{paymentId}/delete")
    public String deleteForm(@PathVariable("paymentId") UUID paymentId) {
        this.websitePaymentRepository.deleteById(paymentId);
        return "redirect:/breach/{breachId}";
    }
}
