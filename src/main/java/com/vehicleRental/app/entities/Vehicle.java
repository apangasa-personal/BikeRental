package com.vehicleRental.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vehicleRental.app.enums.VehicleStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TBL_VEHICLE")
@XmlRootElement
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Vehicle extends BaseEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private long vehicle_id;

	@Column(name = "registrationNumber", nullable = false)
	private String registrationNumber;

	@Column(name = "vehicleStatus",nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private VehicleStatus vehicleStatus;

	@ManyToOne
	@JoinColumn(name = "centre_id")
	private Centre centre;
}
