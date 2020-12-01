package ua.gov.cyberpolice.breach.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "participant", schema = "breach")
public class Participant extends BaseEntity {

    @Column
    private UUID breachId;

    @ManyToOne
    private Person person;

    @ManyToOne
    private ParticipantType participantType;
}
