package com.vehicleRental.app.controller;

import com.vehicleRental.app.payloads.ApiResponse;
import com.vehicleRental.app.payloads.CentreDto;
import com.vehicleRental.app.payloads.VehicleDto;
import com.vehicleRental.app.service.CentreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/centre")
public class CentreController {
    @Autowired
    private CentreService centreService;

    // create centre
    @PostMapping("/")
    public ResponseEntity<CentreDto> createCentre(@Valid @RequestBody CentreDto centreDto) {
        CentreDto createCentreDto = this.centreService.createCentre(centreDto);
        return new ResponseEntity<>(createCentreDto, HttpStatus.CREATED);
    }

    // update centre
    @PutMapping("/{centreId}")
    public ResponseEntity<CentreDto> updateCentre(@Valid @RequestBody CentreDto centreDto, @PathVariable("centreId") Long uid) {
        CentreDto updatedCentre = this.centreService.updateCentre(centreDto, uid);
        return ResponseEntity.ok(updatedCentre);
    }

    // get all centres
    @GetMapping("/")
    public ResponseEntity<List<CentreDto>> getAllCentres() {
        return ResponseEntity.ok(this.centreService.getAllCentres());
    }

    // get a centre
    @GetMapping("/{centreId}")
    public ResponseEntity<CentreDto> getSingleCentre(@PathVariable Long centreId) {
        return ResponseEntity.ok(this.centreService.getCentreById(centreId));
    }

    // get multiple centre
    @GetMapping("/centresByIDs/")
    public ResponseEntity<List<CentreDto>> getMultipleCentre(@RequestParam List<Long> centreIds) {
        return ResponseEntity.ok(this.centreService.findByIdIn(centreIds));
    }

    @DeleteMapping("/{centreId}")
    public ResponseEntity<ApiResponse> deleteCentre(@PathVariable("centreId") Long uid) {
        this.centreService.deleteCentre(uid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Centre deleted Successfully", true), HttpStatus.OK);
    }

}
