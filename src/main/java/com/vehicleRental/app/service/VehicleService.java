package com.vehicleRental.app.service;

import com.vehicleRental.app.enums.VehicleStatus;
import com.vehicleRental.app.payloads.CentreDto;
import com.vehicleRental.app.payloads.VehicleDto;

import java.util.List;

public interface VehicleService {

	VehicleDto createVehicle(VehicleDto user, Long centreId);

	abstract VehicleDto updateVehicle(VehicleDto vehicleDto, Long vehicleId);

	VehicleDto getVehicleById(Long userId);

	List<VehicleDto> getAllVehicles();

	void deleteVehicle(Long userId);

	List<VehicleDto> getVehiclesByCentre(Long centreId);

	List<VehicleDto> getVehiclesByCentreAndStatus(Long centreId, Integer vehicleStatus);

	List<VehicleDto> getVehiclesByStatusAndCentreIn(Integer vehicleStatus, List<Long> centres);

	List<VehicleDto> getVehiclesWithinRadius(Double longitude, Double latitude,Integer range, Integer status);
}
