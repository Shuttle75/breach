package ua.gov.cyberpolice.breach.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "person", schema = "breach")
public class Person extends BaseEntity {

    @Column(nullable = false)
    private String passport;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
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

    @Column
    private String email;

    @Column
    private byte[] image;


    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime modified;

    public String getAddress() {
        if (city == null || city.equals("")) {
            return region.getName() + ", " + street + " " + house + ", КВ." + apartment;
        } else {
            return region.getName() + ", " + city + ", " + street + " " + house + ", КВ." + apartment;
        }
    }
}
