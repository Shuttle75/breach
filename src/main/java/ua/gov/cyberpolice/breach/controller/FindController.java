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
import ua.gov.cyberpolice.breach.repository.DictionaryRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class FindController {

    @PersistenceContext
    EntityManager entityManager;

    private static final String VIEWS_BREACH_FIND_FORM = "breach/findBreach";
    private static final String VIEWS_BREACH_DICTIONARY_FORM = "breach/createOrUpdateDictionaryForm";

    private final DictionaryRepository dictionaryRepository;

    public FindController (
            DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }


    @GetMapping("breach/find")
    public String initCreationForm(Find find, Map<String, Object> model) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QBreach breach = QBreach.breach;

        BooleanBuilder predicate = new BooleanBuilder();

//        if(!"".equals(find.getCardNumber())) {
//            predicate.and(breach.bankCards.any().cardNumber.contains(find.getCardNumber()));
//        }
//
//        if(!"".equals(find.getPassport())) {
//            predicate.and(breach.bankCards.any().holder.passport.contains(find.getPassport())
//                    .or(breach.participants.any().person.passport.contains(find.getPassport())));
//        }
//
//        if(!"".equals(find.getLastName())) {
//            predicate.and(breach.bankCards.any().holder.lastName.contains(find.getLastName())
//                    .or(breach.participants.any().person.lastName.contains(find.getLastName())));
//        }
//
//        if(!"".equals(find.getFirstName())) {
//            predicate.and(breach.bankCards.any().holder.firstName.contains(find.getFirstName())
//                    .or(breach.participants.any().person.firstName.contains(find.getFirstName())));
//        }
//
//        if(!"".equals(find.getMiddleName())) {
//            predicate.and(breach.bankCards.any().holder.middleName.contains(find.getMiddleName())
//                    .or(breach.participants.any().person.middleName.contains(find.getMiddleName())));
//        }

        OrderSpecifier<String> specifier =
                new OrderSpecifier<>(
                        Order.valueOf(find.getSortOrder()),
                        Expressions.path(String.class, QBreach.breach, find.getSortField()));

        List<Breach> breaches = queryFactory
                .selectFrom(breach)
                .where(predicate)
                .orderBy(specifier)
                .fetch();

        if (find.getCurrentPage() < 1) {
            find.setCurrentPage(1);
        }

        find.setPages(new ArrayList<>());
        find.getPages().add(find.new Pages(find.getFirstPage().toString(),"|<", ""));
        find.getPages().add(find.new Pages(find.getPageSize() + "--","<<", ""));
        for (Integer i = find.getPrevPage(); i < find.getNextPage(); i++) {
            if (i.equals(find.getCurrentPage())) {
                find.getPages().add(find.new Pages(i.toString(), String.valueOf(i), "active"));
            } else {
                find.getPages().add(find.new Pages(i.toString(), String.valueOf(i), null));
            }
        }
        find.getPages().add(find.new Pages(find.getNextPage() + "++",">>", ""));
        find.getPages().add(find.new Pages(find.getLastPage().toString(),">|", ""));

        find.setTotalItems(breaches.size());
        if (find.getCurrentPage() <= 10) {
            find.setFirstPage(1);
            find.setPrevPage(1);
            find.setNextPage(11);
        }
        find.setLastPage(find.getTotalItems() % find.getPageSize());

        model.put("breaches", breaches);
        return VIEWS_BREACH_FIND_FORM;
    }

    @PostMapping("breach/find")
    public String processCreationForm(Find find, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("find", find);
        return "redirect:/breach/find";
    }
}
