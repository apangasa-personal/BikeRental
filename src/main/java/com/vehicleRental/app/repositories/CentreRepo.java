package com.vehicleRental.app.repositories;

import com.vehicleRental.app.entities.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentreRepo extends JpaRepository<Centre, Long> {

}
