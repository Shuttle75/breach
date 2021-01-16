package ua.gov.cyberpolice.breach.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "call_data_record", schema = "breach")
public class CallDataRecord extends BaseEntity {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column
    private LocalDateTime date;

    @Column
    private String type;

    @Column
    private String imsi;

    @Column
    private String imei;

    @Column
    private String originator;

    @Column
    private String terminator;

    @Column
    private String cellAddress;

    @Column
    private Float cellLatitude;

    @Column
    private Float cellLongitude;

    @Column
    private Float cellAzimuth;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getTerminator() {
        return terminator;
    }

    public void setTerminator(String terminator) {
        this.terminator = terminator;
    }

    public String getCellAddress() {
        return cellAddress;
    }

    public void setCellAddress(String cellAddress) {
        this.cellAddress = cellAddress;
    }

    public Float getCellLatitude() {
        return cellLatitude;
    }

    public void setCellLatitude(Float cellLatitude) {
        this.cellLatitude = cellLatitude;
    }

    public Float getCellLongitude() {
        return cellLongitude;
    }

    public void setCellLongitude(Float cellLongitude) {
        this.cellLongitude = cellLongitude;
    }

    public Float getCellAzimuth() {
        return cellAzimuth;
    }

    public void setCellAzimuth(Float cellAzimuth) {
        this.cellAzimuth = cellAzimuth;
    }
}
