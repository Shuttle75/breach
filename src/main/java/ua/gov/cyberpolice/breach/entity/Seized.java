package ua.gov.cyberpolice.breach.entity;

import javax.persistence.*;

@Entity
@Table(name = "seized", schema = "breach")
public class Seized extends BaseEntity {

    @OneToOne
    private Breach breach;

    @OneToOne
    private BankCard bankCard;

    public Breach getBreach() {
        return breach;
    }

    public void setBreach(Breach breach) {
        this.breach = breach;
    }

    public BankCard getBankCard() {
        return bankCard;
    }

    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }
}
