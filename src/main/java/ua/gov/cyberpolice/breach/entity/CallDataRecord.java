package ua.gov.cyberpolice.breach.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "call_data_record", schema = "breach")
public class CallDataRecord extends BaseEntity {

    @Column
    private String type;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column
    private LocalDateTime date;

    @Column
    private String imsi;

    @Column
    private String imei;

    @Column
    private String originator;

    @Column
    private String terminator;

    @Column
    private String baseStation;

    @Column
    private UUID breachId;
}
