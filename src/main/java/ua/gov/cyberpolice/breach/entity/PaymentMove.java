package ua.gov.cyberpolice.breach.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "payment_move", schema = "breach")
public class PaymentMove extends BaseEntity {

    @OneToMany(mappedBy = "headId")
    private List<Payment> payments;

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
