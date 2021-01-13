package ua.gov.cyberpolice.breach.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "confiscated", schema = "breach")
public class Confiscated extends BaseEntity {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column
    private LocalDateTime date;

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

    @OneToMany(mappedBy = "headId")
    private List<BankCard> bankCards;

    @OneToMany(mappedBy = "headId")
    private List<Phone> phones;

    @OneToMany(mappedBy = "headId")
    private List<SimCard> simCards;

    public String getDateFormatted() {
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public String getAddress() {
        if (city == null || city.equals("")) {
            return region.getName() + ", " + street + " " + house + ", КВ." + apartment;
        } else {
            return region.getName() + ", " + city + ", " + street + " " + house + ", КВ." + apartment;
        }
    }
}
