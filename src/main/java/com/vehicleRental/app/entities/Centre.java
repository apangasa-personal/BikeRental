package com.vehicleRental.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TBL_CENTRES")
@XmlRootElement
@Getter
@Setter
@ToString
public class Centre  extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long centre_id;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "centre",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Vehicle> vehicles=new ArrayList<>();
}
