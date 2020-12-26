package ua.gov.cyberpolice.breach.controller;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.gov.cyberpolice.breach.entity.Breach;
import ua.gov.cyberpolice.breach.entity.Find;
import ua.gov.cyberpolice.breach.entity.QBreach;
import ua.gov.cyberpolice.breach.repository.BreachRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Controller
public class FindController {

    @PersistenceContext
    EntityManager entityManager;

    private static final String VIEWS_BREACH_FIND_FORM = "breach/findBreach";

    private final BreachRepository breachRepository;

    public FindController(
            BreachRepository breachRepository) {
        this.breachRepository = breachRepository;
    }

    @GetMapping("breach/find")
    public String initCreationForm(Find find, Map<String, Object> model) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        OrderSpecifier<String> specifier =
                new OrderSpecifier<>(
                        Order.valueOf(find.getSortOrder()),
                        Expressions.path(String.class, QBreach.breach, find.getSortField()));

        List<Breach> breaches = queryFactory
                .select(QBreach.breach)
                .from(QBreach.breach)
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
