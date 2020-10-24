package ua.gov.cyberpolice.breach.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "person", schema = "breach")
public class Person extends BaseEntity {

    @Column
    String passport;

    @Column
    String lastName;

    @Column
    String firstName;

    @Column
    String middleName;

    @Column
    LocalDate birthDate;

    @Column
    String region;

    @Column
    String city;

    @Column
    String street;

    @Column
    String house;

    @Column
    String apartment;

    public String getFullInfo() {
        return passport + " " +
                lastName + " " +
                firstName + " " +
                middleName + " " +
                birthDate + " " +
                region + " " +
                city + "" +
                street + " " +
                house;
    }
}
