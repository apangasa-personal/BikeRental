package com.vehicleRental.app.controller;

import com.vehicleRental.app.payloads.ApiResponse;
import com.vehicleRental.app.payloads.VehicleDto;
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

    // create vehicle
    @PostMapping("/")
    public ResponseEntity<VehicleDto> createVehicle(@Valid @RequestBody VehicleDto vehicleDto) {
        VehicleDto createVehicleDto = this.vehicleService.createVehicle(vehicleDto);
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

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<ApiResponse> deleteVehicle(@PathVariable("vehicleId") Long uid) {
        this.vehicleService.deleteVehicle(uid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Vehicle deleted Successfully", true), HttpStatus.OK);
    }
}
