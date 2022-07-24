package com.vehicleRental.app.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "TBL_CENTRES")
@XmlRootElement
@Getter
@Setter
@ToString
public class Centre  extends BaseEntity implements Serializable {

    @Column(name = "longitude", nullable = false)
    private long longitude;

    @Column(name = "latitude", nullable = false)
    private long latitude;

    @Column(name = "phone")
    private String phone;
}
