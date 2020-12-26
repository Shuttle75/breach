package ua.gov.cyberpolice.breach.controller;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.gov.cyberpolice.breach.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Controller
public class FindController {

    @PersistenceContext
    EntityManager entityManager;

    private static final String VIEWS_BREACH_FIND_FORM = "breach/findBreach";

    @GetMapping("breach/find")
    public String initCreationForm(Find find, Map<String, Object> model) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QBreach breach = QBreach.breach;

        BooleanBuilder predicate = new BooleanBuilder();

        if(!"".equals(find.getCardNumber())) {
            predicate.and(breach.usedBankCards.any().cardNumber.contains(find.getCardNumber()));
        }

        if(!"".equals(find.getPassport())) {
            predicate.and(breach.usedBankCards.any().holder.passport.contains(find.getPassport())
                    .or(breach.participants.any().person.passport.contains(find.getPassport())));
        }

        if(!"".equals(find.getLastName())) {
            predicate.and(breach.usedBankCards.any().holder.lastName.contains(find.getLastName())
                    .or(breach.participants.any().person.lastName.contains(find.getLastName())));
        }

        if(!"".equals(find.getFirstName())) {
            predicate.and(breach.usedBankCards.any().holder.firstName.contains(find.getFirstName())
                    .or(breach.participants.any().person.firstName.contains(find.getFirstName())));
        }

        if(!"".equals(find.getMiddleName())) {
            predicate.and(breach.usedBankCards.any().holder.middleName.contains(find.getMiddleName())
                    .or(breach.participants.any().person.middleName.contains(find.getMiddleName())));
        }

        OrderSpecifier<String> specifier =
                new OrderSpecifier<>(
                        Order.valueOf(find.getSortOrder()),
                        Expressions.path(String.class, QBreach.breach, find.getSortField()));

        List<Breach> breaches = queryFactory
                .selectFrom(breach)
                .where(predicate)
                .orderBy(specifier)
                .fetch();

        model.put("breaches", breaches);
        return VIEWS_BREACH_FIND_FORM;
    }

    @PostMapping("breach/find")
    public String processCreationForm(Find find, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("find", find);
        return "redirect:/breach/find";
    }
}
