package com.vehicleRental.app.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TBL_USERS")
@XmlRootElement
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User extends BaseEntity implements Serializable{

	@Column(name = "name")
	private String name;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "dlNumber", nullable = false)
	private String dlNumber;
}
