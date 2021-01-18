package ua.gov.cyberpolice.breach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.gov.cyberpolice.breach.entity.Person;
import ua.gov.cyberpolice.breach.entity.WalletPayment;
import ua.gov.cyberpolice.breach.repository.RegionRepository;
import ua.gov.cyberpolice.breach.repository.WalletPaymentRepository;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/breach/{breachId}")
public class WalletPaymentController {
    private static final String VIEWS_CREATE_OR_UPDATE_FORM = "breach/createOrUpdateWalletPaymentForm";

    private final WalletPaymentRepository walletPaymentRepository;
    private final RegionRepository regionRepository;

    public WalletPaymentController(
            WalletPaymentRepository walletPaymentRepository,
            RegionRepository regionRepository) {
        this.walletPaymentRepository = walletPaymentRepository;
        this.regionRepository = regionRepository;
    }

    @GetMapping("wallet-payment")
    public String initCreationForm(@PathVariable("breachId") UUID breachId, ModelMap model) {
        WalletPayment payment = new WalletPayment();
        Person person = new Person();

        regionRepository.findById("8000000000")
                .ifPresent(person::setRegion);
        payment.setHeadId(breachId);

        model.put("payment", payment);
        return VIEWS_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("wallet-payment/{paymentId}/edit")
    public String initEditForm(@PathVariable("paymentId") UUID paymentId, ModelMap model) {
        this.walletPaymentRepository.findById(paymentId)
                .ifPresent(payment -> model.put("payment", payment));
        return VIEWS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping({"wallet-payment", "wallet-payment/{paymentId}/edit"})
    public String processForm(
            @Valid WalletPayment walletPayment,
            BindingResult result,
            ModelMap model) {
        if (result.hasErrors()) {
            model.put("wallet", walletPayment);
            return VIEWS_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.walletPaymentRepository.save(walletPayment);
            return "redirect:/breach/{breachId}";
        }
    }

    @GetMapping("wallet-payment/{paymentId}/delete")
    public String deleteForm(@PathVariable("paymentId") UUID paymentId) {
        this.walletPaymentRepository.deleteById(paymentId);
        return "redirect:/breach/{breachId}";
    }
}
