package ua.gov.cyberpolice.breach.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "wallet", schema = "breach")
public class Wallet extends BaseEntity {

    @ManyToOne
    private PaymentProvider paymentProvider;

    @Column
    private String walletNumber;
}
