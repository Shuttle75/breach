package ua.gov.cyberpolice.breach.entity;

import javax.persistence.*;

@Entity
@Table(name = "breach_method", schema = "breach")
public class BreachMethod {

    @Id
    private Integer id;

    @ManyToOne
    private BreachType breachType;

    @Column
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BreachType getBreachType() {
        return breachType;
    }

    public void setBreachType(BreachType breachType) {
        this.breachType = breachType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
