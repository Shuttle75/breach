package ua.gov.cyberpolice.breach.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "phone", schema = "breach")
public class Phone extends BaseEntity {

    @Column
    private String imei1;

    @Column
    private String imei2;

    @Column
    private String mac;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Person holder;

    public String getImei1() {
        if(imei1 != null && imei1.length() == 15) {
            return imei1.substring(0, 2) + " " +
                    imei1.substring(2, 8) + " " +
                    imei1.substring(8, 14) + " " +
                    imei1.charAt(14);
        }
        return imei1;
    }

    public void setImei1(String cardNumber) {
        this.imei1 = cardNumber.replace(" ", "");
    }
}
