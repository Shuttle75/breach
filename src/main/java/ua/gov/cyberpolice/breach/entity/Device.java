package ua.gov.cyberpolice.breach.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "seized", schema = "breach")
public class Device extends BaseEntity {

    @Column
    private Integer type;

    @Column
    private Long macAddress;

    @Column
    private Long imei1;

    @Column
    private Long imei2;

    @Column
    private Long ipv4;

    @Column
    private Long ipv6High;

    @Column
    private Long ipv6Low;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(Long macAddress) {
        this.macAddress = macAddress;
    }

    public Long getImei1() {
        return imei1;
    }

    public void setImei1(Long imei1) {
        this.imei1 = imei1;
    }

    public Long getImei2() {
        return imei2;
    }

    public void setImei2(Long imei2) {
        this.imei2 = imei2;
    }

    public Long getIpv4() {
        return ipv4;
    }

    public void setIpv4(Long ipv4) {
        this.ipv4 = ipv4;
    }

    public Long getIpv6High() {
        return ipv6High;
    }

    public void setIpv6High(Long ipv6High) {
        this.ipv6High = ipv6High;
    }

    public Long getIpv6Low() {
        return ipv6Low;
    }

    public void setIpv6Low(Long ipv6Low) {
        this.ipv6Low = ipv6Low;
    }
}
