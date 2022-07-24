package com.vehicleRental.app.service;

import com.vehicleRental.app.payloads.VehicleDto;

import java.util.List;

public interface VehicleService {

	VehicleDto createVehicle(VehicleDto user);

	VehicleDto updateVehicle(VehicleDto user, Long userId);

	VehicleDto getVehicleById(Long userId);

	List<VehicleDto> getAllVehicles();

	void deleteVehicle(Long userId);
}
