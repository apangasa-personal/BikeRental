package com.vehicleRental.app.payloads;

import com.vehicleRental.app.entities.BaseEntity;
import com.vehicleRental.app.enums.VehicleStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Setter
@Getter
public class VehicleDto extends BaseEntity {

	@NotEmpty( message = "Registration number should be greater than empty")
	private String registrationNumber;

	private String vehicleStatus;

}
