package ua.gov.cyberpolice.breach.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "website_payment", schema = "breach")
public class WebsitePayment extends Payment {

    @Column
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String getIcon() {
        return "glyphicon glyphicon-globe";
    }

    @Override
    public String getInfo() {
        return this.link;
    }

    @Override
    public String getEditLink() {
        return "website-payment/" + this.getId() + "/edit";
    }
}
