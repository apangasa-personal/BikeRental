package com.vehicleRental.app.service.impl;

import com.vehicleRental.app.entities.Vehicle;
import com.vehicleRental.app.exception.ResourceNotFoundException;
import com.vehicleRental.app.payloads.VehicleDto;
import com.vehicleRental.app.repositories.VehicleRepo;
import com.vehicleRental.app.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
	@Autowired
	private VehicleRepo vehicleRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public VehicleDto createVehicle(VehicleDto vehicleDto) {
		Vehicle vehicle = this.dtoToVehicle(vehicleDto);
		Vehicle savedVehicle = this.vehicleRepo.save(vehicle);
		return this.vehicleToDto(savedVehicle);
	}

	@Override
	public VehicleDto updateVehicle(VehicleDto vehicleDto, Long vehicleId) {

		Vehicle vehicle = this.vehicleRepo.findById(vehicleId)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle", " Id ", vehicleId));

		vehicle.setVehicleStatus(vehicleDto.getVehicleStatus());
		vehicle.setRegistrationNumber(vehicleDto.getRegistrationNumber());

		Vehicle updatedVehicle = this.vehicleRepo.save(vehicle);
		VehicleDto vehicleDto1 = this.vehicleToDto(updatedVehicle);
		return vehicleDto1;
	}

	@Override
	public VehicleDto getVehicleById(Long vehicleId) {

		Vehicle vehicle = this.vehicleRepo.findById(vehicleId)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle", " Id ", vehicleId));

		return this.vehicleToDto(vehicle);
	}

	@Override
	public List<VehicleDto> getAllVehicles() {

		List<Vehicle> vehicles = this.vehicleRepo.findAll();
		List<VehicleDto> vehicleDtos = vehicles.stream().map(vehicle -> this.vehicleToDto(vehicle)).collect(Collectors.toList());

		return vehicleDtos;
	}

	@Override
	public void deleteVehicle(Long vehicleId) {
		Vehicle vehicle = this.vehicleRepo.findById(vehicleId)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle", "Id", vehicleId));
		this.vehicleRepo.delete(vehicle);

	}

	public Vehicle dtoToVehicle(VehicleDto vehicleDto) {
		Vehicle vehicle = this.modelMapper.map(vehicleDto, Vehicle.class);
		return vehicle;
	}

	public VehicleDto vehicleToDto(Vehicle vehicle) {
		VehicleDto vehicleDto = this.modelMapper.map(vehicle, VehicleDto.class);
		return vehicleDto;
	}
}
