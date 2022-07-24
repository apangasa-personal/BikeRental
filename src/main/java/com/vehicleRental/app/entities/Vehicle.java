package com.vehicleRental.app.entities;

import com.vehicleRental.app.enums.VehicleStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "TBL_VEHICLE")
@XmlRootElement
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Vehicle extends BaseEntity implements Serializable{

	@Column(name = "registrationNumber", nullable = false)
	private String registrationNumber;

	@Column(name = "vehicleStatus")
	@Enumerated(EnumType.STRING)
	private String vehicleStatus;
}
