package ua.gov.cyberpolice.breach.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "breach_method", schema = "breach")
public class BreachMethod {

    @Id
    private Integer id;

    @ManyToOne
    private BreachType breachType;

    @Column
    private String name;
}
