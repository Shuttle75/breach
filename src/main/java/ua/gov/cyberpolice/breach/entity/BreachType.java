package ua.gov.cyberpolice.breach.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "breach_type", schema = "breach")
public class BreachType {

    @Id
    private Integer id;

    @Column
    private String name;
}
