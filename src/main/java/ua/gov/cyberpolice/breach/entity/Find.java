package ua.gov.cyberpolice.breach.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class Find {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fromDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate toDate;

    private Integer page = 1;

    private Integer size = 10;

    private String sortField = "incomeDate";

    private Boolean sortAsc = true;

    private String passport;

    private String lastName;

    private String firstName;

    private String middleName;

    private String region;

    private Integer method;

    private String cardNumber;

    private String phone;

    private String mac;

    private String imei;
}
