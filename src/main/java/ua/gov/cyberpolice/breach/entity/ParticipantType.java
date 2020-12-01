package ua.gov.cyberpolice.breach.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "participant_type", schema = "breach")
public class ParticipantType extends BaseEntity {

}
