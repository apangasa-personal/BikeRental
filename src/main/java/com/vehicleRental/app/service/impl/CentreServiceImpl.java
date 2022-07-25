package com.vehicleRental.app.service.impl;

import com.vehicleRental.app.entities.Centre;
import com.vehicleRental.app.exception.ResourceNotFoundException;
import com.vehicleRental.app.payloads.CentreDto;
import com.vehicleRental.app.repositories.CentreRepo;
import com.vehicleRental.app.service.CentreService;
import com.vehicleRental.app.util.DistanceUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CentreServiceImpl implements CentreService {
	@Autowired
	private CentreRepo centreRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CentreDto createCentre(CentreDto centreDto) {
		Centre centre = this.dtoToCentre(centreDto);
		Centre savedCentre = this.centreRepo.save(centre);
		return this.centreToDto(savedCentre);
	}

	@Override
	public CentreDto updateCentre(CentreDto centreDto, Long centreId) {

		Centre centre = this.centreRepo.findById(centreId)
				.orElseThrow(() -> new ResourceNotFoundException("Centre", " Id ", centreId));

		centre.setLongitude(centreDto.getLongitude());
		centre.setLatitude(centreDto.getLatitude());
		centre.setPhone(centreDto.getPhone());

		Centre updatedCentre = this.centreRepo.save(centre);
		CentreDto centreDto1 = this.centreToDto(updatedCentre);
		return centreDto1;
	}

	@Override
	public CentreDto getCentreById(Long centreId) {
		Centre centre = this.centreRepo.findById(centreId)
				.orElseThrow(() -> new ResourceNotFoundException("Centre", " Id ", centreId));

		return this.centreToDto(centre);
	}

	@Override
	public List<CentreDto> findByIdIn(List<Long> centreIds) {
		List<Centre> centres = this.centreRepo.findAllById(centreIds);
		List<CentreDto> centreDtos = centres.stream().map(centre -> this.centreToDto(centre)).collect(Collectors.toList());

		return centreDtos;
	}

	@Override
	public List<CentreDto> getAllCentres() {

		List<Centre> centres = this.centreRepo.findAll();
		List<CentreDto> centreDtos = centres.stream().map(centre -> this.centreToDto(centre)).collect(Collectors.toList());

		return centreDtos;
	}

	@Override
	public void deleteCentre(Long centreId) {
		Centre centre = this.centreRepo.findById(centreId)
				.orElseThrow(() -> new ResourceNotFoundException("Centre", "Id", centreId));
		this.centreRepo.delete(centre);

	}

	@Override
	public List<Centre> getCentreWithinRadius(Double longitudeFrom, Double latitudeFrom, Integer distanceRange){
		List<Centre> centres = this.centreRepo.findAll();
		List<Centre> centresWithinRadius = centres.stream()
				.filter(centre -> DistanceUtil.distance(latitudeFrom, longitudeFrom, centre.getLatitude(), centre.getLongitude()) < distanceRange)
				.collect(Collectors.toList());

		//List<CentreDto> centresWithinRadiusDtos = centresWithinRadius.stream().map(centre -> this.centreToDto(centre)).collect(Collectors.toList());

		return centresWithinRadius;
	}

	public List<Centre> getAllCentresEntity(Double longitudeFrom, Double latitudeFrom, Integer distanceRange) {
		List<Centre> centres = getCentreWithinRadius(longitudeFrom, latitudeFrom, distanceRange);
		return centres;
	}

	public Centre dtoToCentre(CentreDto centreDto) {
		Centre centre = this.modelMapper.map(centreDto, Centre.class);
		return centre;
	}

	public CentreDto centreToDto(Centre centre) {
		CentreDto centreDto = this.modelMapper.map(centre, CentreDto.class);
		return centreDto;
	}


}
