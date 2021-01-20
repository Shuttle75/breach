package ua.gov.cyberpolice.breach.entity;

import javax.persistence.*;

@Entity
@Table(name = "prison", schema = "breach")
public class Prison {

    @Id
    private Integer id;

    @Column
    private String name;

    @Column
    private String code;

    @Column
    private Float cellLatitude;

    @Column
    private Float cellLongitude;
}
