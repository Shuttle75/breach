package ua.gov.cyberpolice.breach.entity;

import javax.persistence.*;

@Entity
@Table(name = "wallet_payment", schema = "breach")
public class WalletPayment extends Payment {

    @ManyToOne
    private PaymentProvider paymentProvider;

    @Column
    private String walletNumber;

    public PaymentProvider getPaymentProvider() {
        return paymentProvider;
    }

    public void setPaymentProvider(PaymentProvider paymentProvider) {
        this.paymentProvider = paymentProvider;
    }

    public String getWalletNumber() {
        return walletNumber;
    }

    public void setWalletNumber(String walletNumber) {
        this.walletNumber = walletNumber;
    }

    @Override
    public String getType() {
        return "Гаманець";
    }

    @Override
    public String getInfo() {
        return "Платіжна система: " + paymentProvider.getName() +
                ", номер гаманця: " + walletNumber;
    }

    @Override
    public String getEditLink() {
        return "walletPayment/" + this.getId() + "/edit";
    }
}
