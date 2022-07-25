package com.vehicleRental.app.repositories;

import com.vehicleRental.app.entities.Centre;
import com.vehicleRental.app.entities.Vehicle;
import com.vehicleRental.app.enums.VehicleStatus;
import com.vehicleRental.app.payloads.CentreDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByCentre(Centre centre);
    List<Vehicle> findByCentreAndVehicleStatus(Centre centre, VehicleStatus vehicleStatus);
    List<Vehicle> findByVehicleStatusAndCentreIn(VehicleStatus vehicleStatus, List<Centre> centres);
}
