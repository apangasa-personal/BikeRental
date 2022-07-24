package com.vehicleRental.app.payloads;

import com.vehicleRental.app.entities.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;

@NoArgsConstructor
@Setter
@Getter
public class CentreDto extends BaseEntity {

	@DecimalMin(value = "0.0000001", message = "Latitude should be greater than 0")
	private long latitude;

	@DecimalMin(value = "0.0000001", message = "Longitude should be greater than 0")
	private long longitude;

	@NotEmpty(message = "phone cannot be empty")
	private String phone;

}
