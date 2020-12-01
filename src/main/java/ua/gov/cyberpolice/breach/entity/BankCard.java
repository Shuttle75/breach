package ua.gov.cyberpolice.breach.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Entity
@Table(name = "bank_card", schema = "breach")
public class BankCard extends BaseEntity {

    @NotEmpty(message = "Введіть номер картки")
    @Column
    private String cardNumber;

    @Column
    private String bankName;

    @Column
    private UUID userId;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Person holder;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Person getHolder() {
        return holder;
    }

    public void setHolder(Person holder) {
        this.holder = holder;
    }
}
