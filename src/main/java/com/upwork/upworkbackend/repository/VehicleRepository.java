package com.upwork.upworkbackend.repository;

import com.upwork.upworkbackend.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository  extends JpaRepository<Vehicle,Long>{

}
