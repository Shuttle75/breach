package ua.gov.cyberpolice.breach.entity;

import javax.persistence.*;

@Entity
@Table(name = "breach_type", schema = "breach")
public class BreachType {

    @Id
    private Integer id;

    @Column
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
