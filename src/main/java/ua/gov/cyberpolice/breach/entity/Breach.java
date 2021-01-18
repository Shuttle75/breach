package ua.gov.cyberpolice.breach.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    private List<Payment> payments;

    @OneToMany(mappedBy = "headId")
    private List<CallDataRecord> callDataRecords;

    @OneToMany(mappedBy = "headId")
    private List<Confiscated> confiscated;

    public Integer getSentId() {
        return sentId;
    }

    public void setSentId(Integer sentId) {
        this.sentId = sentId;
    }

    public String getSentNumber() {
        return sentNumber;
    }

    public void setSentNumber(String sentNumber) {
        this.sentNumber = sentNumber;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    public String getIncomeNumber() {
        return incomeNumber;
    }

    public void setIncomeNumber(String incomeNumber) {
        this.incomeNumber = incomeNumber;
    }

    public LocalDateTime getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(LocalDateTime incomeDate) {
        this.incomeDate = incomeDate;
    }

    public String getEoNumber() {
        return eoNumber;
    }

    public void setEoNumber(String eoNumber) {
        this.eoNumber = eoNumber;
    }

    public LocalDateTime getEoDate() {
        return eoDate;
    }

    public void setEoDate(LocalDateTime eoDate) {
        this.eoDate = eoDate;
    }

    public String getErdrNumber() {
        return erdrNumber;
    }

    public void setErdrNumber(String erdrNumber) {
        this.erdrNumber = erdrNumber;
    }

    public LocalDateTime getErdrDate() {
        return erdrDate;
    }

    public void setErdrDate(LocalDateTime erdrDate) {
        this.erdrDate = erdrDate;
    }

    public String getErdrArticle() {
        return erdrArticle;
    }

    public void setErdrArticle(String erdrArticle) {
        this.erdrArticle = erdrArticle;
    }

    public BreachMethod getBreachMethod() {
        return breachMethod;
    }

    public void setBreachMethod(BreachMethod breachMethod) {
        this.breachMethod = breachMethod;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getOperInfo() {
        return operInfo;
    }

    public void setOperInfo(String operInfo) {
        this.operInfo = operInfo;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<CallDataRecord> getCallDataRecords() {
        return callDataRecords;
    }

    public void setCallDataRecords(List<CallDataRecord> callDataRecords) {
        this.callDataRecords = callDataRecords;
    }

    public List<Confiscated> getConfiscated() {
        return confiscated;
    }

    public void setConfiscated(List<Confiscated> confiscated) {
        this.confiscated = confiscated;
    }
}
