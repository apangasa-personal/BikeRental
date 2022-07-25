package com.vehicleRental.app.payloads;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.vehicleRental.app.entities.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto extends BaseEntity {

	@Column(name = "user_name", nullable = false, length = 100)
	@NotEmpty(message = "Name cannot be empty")
	private String name;

	@Email(message = "Email id is invalid!")
	private String email;

	@NotEmpty(message = "DL Number is required")
	@Size(min = 10, max = 14, message = "DL Number length should be between 10 and 14 inclusive!")
	private String dlNumber;

}
