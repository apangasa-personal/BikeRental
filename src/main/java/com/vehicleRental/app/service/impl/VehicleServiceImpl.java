package com.vehicleRental.app.service.impl;

import com.vehicleRental.app.entities.Centre;
import com.vehicleRental.app.entities.Vehicle;
import com.vehicleRental.app.enums.VehicleStatus;
import com.vehicleRental.app.exception.ResourceNotFoundException;
import com.vehicleRental.app.payloads.CentreDto;
import com.vehicleRental.app.payloads.VehicleDto;
import com.vehicleRental.app.repositories.CentreRepo;
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

	@Autowired
	private CentreRepo centreRepo;

	@Autowired
	private CentreServiceImpl centreService;

	@Override
	public VehicleDto createVehicle(VehicleDto vehicleDto, Long centreId) {
		Centre centre = this.centreRepo.findById(centreId)
				.orElseThrow(() -> new ResourceNotFoundException("Centre ", "Centre id", centreId));
		Vehicle vehicle = this.dtoToVehicle(vehicleDto);
		vehicle.setCentre(centre);
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

	@Override
	public List<VehicleDto> getVehiclesByCentre(Long centreId) {

		Centre centre = this.centreRepo.findById(centreId)
				.orElseThrow(() -> new ResourceNotFoundException("Centre ", "centreId ", centreId));
		List<Vehicle> vehicles = this.vehicleRepo.findByCentre(centre);

		List<VehicleDto> vehicleDtos = vehicles.stream().map((vehicle) -> this.modelMapper.map(vehicle, VehicleDto.class))
				.collect(Collectors.toList());

		return vehicleDtos;
	}

	@Override
	public List<VehicleDto> getVehiclesByCentreAndStatus(Long centreId, Integer vehicleStatus) {

		Centre centre = this.centreRepo.findById(centreId)
				.orElseThrow(() -> new ResourceNotFoundException("Centre ", "centreId ", centreId));
		List<Vehicle> vehicles = this.vehicleRepo.findByCentreAndVehicleStatus(centre, VehicleStatus.fromOrdinal(vehicleStatus));

		List<VehicleDto> vehicleDtos = vehicles.stream().map((vehicle) -> this.modelMapper.map(vehicle, VehicleDto.class))
				.collect(Collectors.toList());

		return vehicleDtos;
	}

	@Override
	public List<VehicleDto> getVehiclesByStatusAndCentreIn(Integer vehicleStatus, List<Long> centresIds) {
		List<Centre> centres = this.centreRepo.findAllById(centresIds);
		List<Vehicle> vehicles = this.vehicleRepo.findByVehicleStatusAndCentreIn(VehicleStatus.fromOrdinal(vehicleStatus), centres);

		List<VehicleDto> vehicleDtos = vehicles.stream().map((vehicle) -> this.modelMapper.map(vehicle, VehicleDto.class))
				.collect(Collectors.toList());

		return vehicleDtos;
	}

	@Override
	public List<VehicleDto> getVehiclesWithinRadius(Double longitude, Double latitude,Integer range, Integer vehicleStatus) {
		List<Centre> centres = centreService.getCentreWithinRadius(latitude, longitude, range);
		List<Vehicle> vehicles = this.vehicleRepo.findByVehicleStatusAndCentreIn(VehicleStatus.fromOrdinal(vehicleStatus), centres);

		List<VehicleDto> vehicleDtos = vehicles.stream().map((vehicle) -> this.modelMapper.map(vehicle, VehicleDto.class))
				.collect(Collectors.toList());

		return vehicleDtos;
	}
}
