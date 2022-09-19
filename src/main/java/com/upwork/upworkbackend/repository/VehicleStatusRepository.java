package com.upwork.upworkbackend.repository;
import com.upwork.upworkbackend.model.VehicleWorkingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.Month;
import java.util.List;

@Repository
public interface VehicleStatusRepository extends JpaRepository <VehicleWorkingStatus,Long>{

}
