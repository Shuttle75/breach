package ua.gov.cyberpolice.breach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.gov.cyberpolice.breach.entity.Breach;
import ua.gov.cyberpolice.breach.entity.Find;
import ua.gov.cyberpolice.breach.repository.BreachRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.awt.*;
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
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Breach> criteriaQuery = criteriaBuilder.createQuery(Breach.class);
        Root<Breach> root = criteriaQuery.from(Breach.class);

        Order order = criteriaBuilder.asc(root.get(find.getSortField()));;
        if (find.getSortAsc()) {
            order.reverse();
        }

        criteriaQuery.select(root)
                .orderBy(order);
        List<Breach> breaches = entityManager.createQuery(criteriaQuery).getResultList();
        model.put("breaches", breaches);
        return VIEWS_BREACH_FIND_FORM;
    }

    @PostMapping("breach/find")
    public String processCreationForm(Find find, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("find", find);
        return "redirect:/breach/find";
    }
}
