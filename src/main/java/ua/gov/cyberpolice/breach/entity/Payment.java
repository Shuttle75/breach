package ua.gov.cyberpolice.breach.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Payment extends BaseEntity {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column
    private LocalDateTime date;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDateFormatted() {
        if (date == null) {
            return "";
        } else {
            return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        }
    }

    public abstract String getType();

    public abstract String getInfo();

    public abstract String getEditLink();
}
