package ua.gov.cyberpolice.breach.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bank_card_payment", schema = "breach")
public class BankCardPayment extends Payment {

    @OneToOne
    private BankCard bankCard;

    public BankCard getBankCard() {
        return bankCard;
    }

    @Override
    public String getType() {
        return "BankCardPayment";
    }

    @Override
    public String getInfo() {
        return null;
    }
}
