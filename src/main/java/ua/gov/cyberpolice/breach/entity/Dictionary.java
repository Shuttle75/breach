package ua.gov.cyberpolice.breach.entity;

import javax.persistence.*;

@Entity
@Table(name = "dictionary", schema = "breach")
public class Dictionary {

    @Id
    private Integer id;

    @Column
    private String link;

    @Column
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
