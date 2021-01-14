package ua.gov.cyberpolice.breach.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "payment_provider", schema = "breach")
public class PaymentProvider {

    @Id
    private Integer id;

    @Column
    private String name;

    @Column
    private String legalName;

    @Column
    private String website;

    @Column
    private String image;

    @Column
    private Boolean local;
}
