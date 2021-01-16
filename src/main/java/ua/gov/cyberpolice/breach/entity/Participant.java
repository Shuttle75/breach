package ua.gov.cyberpolice.breach.entity;

import javax.persistence.*;

@Entity
@Table(name = "participant", schema = "breach")
public class Participant extends BaseEntity {

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Person person;

    @ManyToOne
    private ParticipantType participantType;

    @Column
    private Integer nsrd;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ParticipantType getParticipantType() {
        return participantType;
    }

    public void setParticipantType(ParticipantType participantType) {
        this.participantType = participantType;
    }

    public Integer getNsrd() {
        return nsrd;
    }

    public void setNsrd(Integer nsrd) {
        this.nsrd = nsrd;
    }
}
