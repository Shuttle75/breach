package ua.gov.cyberpolice.breach.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
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


}
