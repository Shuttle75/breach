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
    private BreachMethod breachMethod;

    @Column
    private String story;

    @ManyToOne
    private Region region;

    @Column
    private String operInfo;

    @OneToMany(mappedBy = "headId")
    private List<Participant> participants;

    @OneToMany(mappedBy = "headId")
    private List<BankCard> bankCards;

    @OneToMany(mappedBy = "headId")
    private List<CallDataRecord> callDataRecords;

    @OneToMany(mappedBy = "headId")
    private List<Wallet> wallets;

    @OneToMany(mappedBy = "headId")
    private List<Website> websites;

    @OneToMany(mappedBy = "headId")
    private List<Confiscated> confiscated;
}
