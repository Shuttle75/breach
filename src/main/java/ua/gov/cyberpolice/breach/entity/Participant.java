package ua.gov.cyberpolice.breach.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "participant", schema = "breach")
public class Participant extends BaseEntity {

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Person person;

    @ManyToOne
    private ParticipantType participantType;

    @Column
    private Integer nsrd;
}
