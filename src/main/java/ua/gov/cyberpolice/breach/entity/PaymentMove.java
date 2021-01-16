package ua.gov.cyberpolice.breach.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "payment_move", schema = "breach")
public class PaymentMove extends BaseEntity {

    @OneToMany(mappedBy = "headId")
    private List<Payment> moneyTransactions;

    public List<Payment> getMoneyTransactions() {
        return moneyTransactions;
    }

    public void setMoneyTransactions(List<Payment> moneyTransactions) {
        this.moneyTransactions = moneyTransactions;
    }
}
