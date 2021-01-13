package ua.gov.cyberpolice.breach.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "sim_card", schema = "breach")
public class SimCard extends BaseEntity {

    @Column
    private String phoneNumber;

    @Column
    private String iccid;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Person holder;
}
