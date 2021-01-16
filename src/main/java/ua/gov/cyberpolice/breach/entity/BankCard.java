package ua.gov.cyberpolice.breach.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "bank_card", schema = "breach")
public class BankCard extends BaseEntity {

    @NotEmpty(message = "Введіть номер картки")
    @Column
    private String cardNumber;

    @Column
    private String bankName;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Person holder;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Person getHolder() {
        return holder;
    }

    public void setHolder(Person holder) {
        this.holder = holder;
    }

    public String getCardNumber() {
        if(cardNumber != null && cardNumber.length() == 16) {
            return cardNumber.substring(0, 4) + " " +
                    cardNumber.substring(4, 8) + " " +
                    cardNumber.substring(8, 12) + " " +
                    cardNumber.substring(12);
        }
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber.replace(" ", "");
    }
}
