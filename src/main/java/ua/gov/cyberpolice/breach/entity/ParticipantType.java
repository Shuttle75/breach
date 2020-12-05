package ua.gov.cyberpolice.breach.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "participant_type", schema = "breach")
public class ParticipantType {

    @Id
    private Integer id;

    @Column
    private String name;

}
