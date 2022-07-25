package com.vehicleRental.app.controller;

import com.vehicleRental.app.enums.VehicleStatus;
import com.vehicleRental.app.payloads.ApiResponse;
import com.vehicleRental.app.payloads.CentreDto;
import com.vehicleRental.app.payloads.VehicleDto;
import com.vehicleRental.app.service.CentreService;
import com.vehicleRental.app.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CentreService centreService;

    // create vehicle
    @PostMapping("/")
    public ResponseEntity<VehicleDto> createVehicle(@Valid @RequestBody VehicleDto vehicleDto, @RequestParam long centreId) {
        VehicleDto createVehicleDto = this.vehicleService.createVehicle(vehicleDto, centreId);
        return new ResponseEntity<>(createVehicleDto, HttpStatus.CREATED);
    }

    // update vehicle
    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleDto> updateVehicle(@Valid @RequestBody VehicleDto vehicleDto, @PathVariable("vehicleId") Long uid) {
        VehicleDto updatedVehicle = this.vehicleService.updateVehicle(vehicleDto, uid);
        return ResponseEntity.ok(updatedVehicle);
    }

    // get all vehicles
    @GetMapping("/")
    public ResponseEntity<List<VehicleDto>> getAllVehicles() {
        return ResponseEntity.ok(this.vehicleService.getAllVehicles());
    }

    // get a vehicle
    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleDto> getSingleVehicle(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(this.vehicleService.getVehicleById(vehicleId));
    }

    //delete a vehicle
    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<ApiResponse> deleteVehicle(@PathVariable("vehicleId") Long uid) {
        this.vehicleService.deleteVehicle(uid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Vehicle deleted Successfully", true), HttpStatus.OK);
    }

    //get vehicle belonging to a centre
    @GetMapping("/centre/{centreId}/vehicles")
    public ResponseEntity<List<VehicleDto>> getVehicleByCentre(@PathVariable Long centreId) {
        List<VehicleDto> posts = this.vehicleService.getVehiclesByCentre(centreId);

        return new ResponseEntity<List<VehicleDto>>(posts, HttpStatus.OK);

    }

    //get vehicle belonging to a centre with specific status
    @GetMapping("/centre/centreID/{centreId}/vehicleStatus/{vehicleStatus}")
    public ResponseEntity<List<VehicleDto>> getPostsByUser(@PathVariable Long centreId, @PathVariable Integer vehicleStatus) {
        List<VehicleDto> vehicles = this.vehicleService.getVehiclesByCentreAndStatus(centreId, vehicleStatus);

        return new ResponseEntity<List<VehicleDto>>(vehicles, HttpStatus.OK);
    }

    // get vehicles belonging to multiple centres with specific status
    @GetMapping("/vehiclesByCentreIdsAndStatus/")
    public ResponseEntity<List<VehicleDto>> getMultipleCentre(@RequestParam List<Long> centreIds, @RequestParam Integer vehicleStatus) {
        List<CentreDto> centres = this.centreService.findByIdIn(centreIds);
        List<VehicleDto> vehicles = this.vehicleService.getVehiclesByStatusAndCentreIn(vehicleStatus, centreIds);
        return new ResponseEntity<List<VehicleDto>>(vehicles, HttpStatus.OK);
    }

    // get vehicles within distance
    @GetMapping("/vehiclesWithinRange/")
    public ResponseEntity<List<VehicleDto>> getDistance(@RequestParam Double longitude, @RequestParam Double latitude, @RequestParam Integer range, @RequestParam Integer status) {
        List<VehicleDto> vehileDtos = this.vehicleService.getVehiclesWithinRadius(longitude, latitude, range, status);
        return new ResponseEntity<List<VehicleDto>>(vehileDtos, HttpStatus.OK);
    }
}
