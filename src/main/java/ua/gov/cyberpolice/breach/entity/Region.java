package ua.gov.cyberpolice.breach.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "region", schema = "breach")
public class Region {

    @Id
    private String id;

    @Column
    private String name;
}
