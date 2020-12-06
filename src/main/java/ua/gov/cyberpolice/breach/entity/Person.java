package ua.gov.cyberpolice.breach.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "person", schema = "breach")
public class Person extends BaseEntity {

    @Column
    private String passport;

    @Column
    private String lastName;

    @Column
    private String firstName;

    @Column
    private String middleName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column
    private LocalDate birthDate;

    @ManyToOne
    private Region region;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private String house;

    @Column
    private String apartment;

    @CreationTimestamp
    @Column
    private LocalDate modified;
}
