package com.jchcranelist.repository;
import com.jchcranelist.model.VehicleWorkingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleStatusRepository extends JpaRepository <VehicleWorkingStatus,Long>{

}
