package com.upwork.upworkbackend.repository;

import com.upwork.upworkbackend.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.time.Year;
import java.util.List;

@Repository
public interface VehicleRepository  extends JpaRepository<Vehicle,Long>{



    List<Vehicle> findAllByMonthAndYear(Month month, Year year);

}
