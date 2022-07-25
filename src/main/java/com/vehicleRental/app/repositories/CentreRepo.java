package com.vehicleRental.app.repositories;

import com.vehicleRental.app.entities.Centre;
import com.vehicleRental.app.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentreRepo extends JpaRepository<Centre, Long> {
}
