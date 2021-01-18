package ua.gov.cyberpolice.breach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.gov.cyberpolice.breach.entity.BankCard;
import ua.gov.cyberpolice.breach.entity.BankCardPayment;
import ua.gov.cyberpolice.breach.entity.Person;
import ua.gov.cyberpolice.breach.repository.BankCardPaymentRepository;
import ua.gov.cyberpolice.breach.repository.RegionRepository;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("breach/{breachId}")
public class BankCardPaymentController {
    private static final String CREATE_OR_UPDATE_FORM = "breach/createOrUpdateBankCardPaymentForm";

    private final BankCardPaymentRepository bankCardPaymentRepository;
    private final RegionRepository regionRepository;

    public BankCardPaymentController(
            BankCardPaymentRepository bankCardPaymentRepository,
            RegionRepository regionRepository) {
        this.bankCardPaymentRepository = bankCardPaymentRepository;
        this.regionRepository = regionRepository;
    }

    @GetMapping("bank-card-payment")
    public String initCreationForm(
            @PathVariable("breachId") UUID breachId,
            ModelMap model) {
        BankCardPayment payment = new BankCardPayment();
        BankCard bankCard = new BankCard();
        Person person = new Person();

        regionRepository.findById("8000000000")
                .ifPresent(person::setRegion);
        bankCard.setHolder(person);
        payment.setHeadId(breachId);
        payment.setBankCard(bankCard);

        model.put("payment", payment);
        return CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("bank-card-payment/{paymentId}/edit")
    public String initEditForm(@PathVariable("paymentId") UUID paymentId, ModelMap model) {
        bankCardPaymentRepository.findById(paymentId)
                .ifPresent(payment -> model.put("payment", payment));
        return CREATE_OR_UPDATE_FORM;
    }

    @PostMapping({"bank-card-payment", "bank-card-payment/{paymentId}/edit"})
    public String processForm(
            @Valid BankCardPayment payment, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.put("payment", payment);
            return CREATE_OR_UPDATE_FORM;
        }
        else {
            this.bankCardPaymentRepository.save(payment);
            return "redirect:/breach/{breachId}";
        }
    }

    @GetMapping("bank-card-payment/{paymentId}/delete")
    public String deleteForm(@PathVariable("paymentId") UUID paymentId) {
        this.bankCardPaymentRepository.deleteById(paymentId);
        return "redirect:/breach/{breachId}";
    }
}
