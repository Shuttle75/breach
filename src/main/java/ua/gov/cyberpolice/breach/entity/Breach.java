package ua.gov.cyberpolice.breach.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "breach", schema = "breach")
public class Breach extends BaseEntity {

    @Column
    private Integer sentId;

    @Column
    private String sentNumber;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column
    private LocalDateTime sentDate;

    @Column
    private String incomeNumber;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column
    private LocalDateTime incomeDate;

    @Column
    private String eoNumber;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column
    private LocalDateTime eoDate;

    @Column
    private String erdrNumber;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column
    private LocalDateTime erdrDate;

    @Column
    private String erdrArticle;

    @ManyToOne
    private Method method;

    @Column
    private String story;

    @ManyToOne
    private Region region;

    @Column
    private String operInfo;

    @OneToMany(mappedBy = "usedId")
    private List<BankCard> usedBankCards;

    @OneToMany(mappedBy = "confiscatedId")
    private List<BankCard> confiscatedBankCards;

    @OneToMany(mappedBy = "breachId")
    private List<Participant> participants;
}
