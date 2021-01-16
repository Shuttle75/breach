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
import ua.gov.cyberpolice.breach.repository.WalletRepository;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/breach/{breachId}")
public class WalletController {
    private static final String VIEWS_CREATE_OR_UPDATE_FORM = "breach/createOrUpdateWalletForm";

    private final WalletRepository walletRepository;
    private final RegionRepository regionRepository;

    public WalletController(
            WalletRepository walletRepository,
            RegionRepository regionRepository) {
        this.walletRepository = walletRepository;
        this.regionRepository = regionRepository;
    }

    @GetMapping("wallet")
    public String initCreationForm(@PathVariable("breachId") UUID breachId, ModelMap model) {
        WalletPayment wallet = new WalletPayment();
        Person person = new Person();

        regionRepository.findById("8000000000")
                .ifPresent(person::setRegion);
        wallet.setHeadId(breachId);

        model.put("wallet", wallet);
        return VIEWS_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("wallet/{walletId}/edit")
    public String initEditForm(@PathVariable("walletId") UUID walletId, ModelMap model) {
        walletRepository.findById(walletId)
                .ifPresent(wallet -> model.put("wallet", wallet));
        return VIEWS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping({"wallet", "wallet/{walletId}/edit"})
    public String processForm(
            @Valid WalletPayment walletPayment,
            BindingResult result,
            ModelMap model) {
        if (result.hasErrors()) {
            model.put("wallet", walletPayment);
            return VIEWS_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.walletRepository.save(walletPayment);
            return "redirect:/breach/{breachId}";
        }
    }

    @GetMapping("wallet/{walletId}/delete")
    public String deleteForm(@PathVariable("walletId") UUID walletId) {
        this.walletRepository.deleteById(walletId);
        return "redirect:/breach/{breachId}";
    }
}
