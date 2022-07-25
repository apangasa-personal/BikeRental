package com.vehicleRental.app.service;

import com.vehicleRental.app.entities.Centre;
import com.vehicleRental.app.payloads.CentreDto;
import com.vehicleRental.app.payloads.VehicleDto;

import java.util.List;

public interface CentreService {

	CentreDto createCentre(CentreDto user);

	CentreDto updateCentre(CentreDto user, Long userId);

	CentreDto getCentreById(Long userId);

	List<CentreDto> findByIdIn(List<Long> userId);

	List<CentreDto> getAllCentres();

	void deleteCentre(Long userId);

	List<Centre> getCentreWithinRadius(Double longitudeFrom, Double latitudeFrom, Integer radius);
}
