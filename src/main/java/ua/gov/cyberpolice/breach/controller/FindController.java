package ua.gov.cyberpolice.breach.controller;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.gov.cyberpolice.breach.entity.*;
import ua.gov.cyberpolice.breach.repository.BreachRepository;

import java.util.Map;

import static ua.gov.cyberpolice.breach.entity.QBreach.breach;

@Controller
public class FindController {

    private static final String VIEWS_BREACH_FIND_FORM = "breach/findBreach";
    private final BreachRepository breachRepository;

    public FindController (BreachRepository breachRepository) {
        this.breachRepository = breachRepository;
    }


    @GetMapping("breach/find")
    public String initCreationForm(Find find, Map<String, Object> model) {
        BooleanBuilder predicate = new BooleanBuilder();
        if(!"".equals(find.getCardNumber())) {
            predicate.and(breach.bankCardPayments.any().bankCard.cardNumber.contains(find.getCardNumber()));
        }

        if(!"".equals(find.getPassport())) {
            predicate.and(breach.bankCardPayments.any().bankCard.holder.passport.contains(find.getPassport())
                    .or(breach.participants.any().person.passport.contains(find.getPassport())));
        }

        if(!"".equals(find.getLastName())) {
            predicate.and(breach.bankCardPayments.any().bankCard.holder.passport.contains(find.getLastName())
                    .or(breach.participants.any().person.passport.contains(find.getLastName())));
        }

        if(!"".equals(find.getFirstName())) {
            predicate.and(breach.bankCardPayments.any().bankCard.holder.firstName.contains(find.getFirstName())
                    .or(breach.participants.any().person.firstName.contains(find.getFirstName())));
        }

        if(!"".equals(find.getMiddleName())) {
            predicate.and(breach.bankCardPayments.any().bankCard.holder.middleName.contains(find.getMiddleName())
                    .or(breach.participants.any().person.middleName.contains(find.getMiddleName())));
        }
        Pageable pageable = PageRequest.of(find.getPageNumber(), 10, Sort.Direction.valueOf(find.getSortOrder()), find.getSortField());
        PageWrapper<Breach> page = new PageWrapper<>(breachRepository.findAll(predicate, pageable), "");

        model.put("page", page);
        return VIEWS_BREACH_FIND_FORM;
    }

    @PostMapping("breach/find")
    public String processCreationForm(Find find, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(find);
        return "redirect:/breach/find";
    }
}
