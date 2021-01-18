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

    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }

    @Override
    public String getType() {
        return "Банківська карта";
    }

    @Override
    public String getInfo() {
        return "карта: " + this.bankCard.getCardNumber() +
                " банк: " + this.bankCard.getBankName() +
                " власник: " + this.bankCard.getHolder().getLastName() +
                " " + this.bankCard.getHolder().getFirstName() +
                " " + this.bankCard.getHolder().getMiddleName();
    }

    @Override
    public String getEditLink() {
        return "bank-card-payment/" + this.getId() + "/edit";
    }
}
