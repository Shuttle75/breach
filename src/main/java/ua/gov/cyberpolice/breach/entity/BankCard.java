package ua.gov.cyberpolice.breach.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "bank_card", schema = "breach")
public class BankCard extends BaseEntity {

    @NotEmpty(message = "Введіть номер картки")
    @Column
    private String cardNumber;

    @Column
    private String bankName;

    @Column
    private UUID usedId;

    @Column
    private UUID confiscatedId;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Person holder;

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
