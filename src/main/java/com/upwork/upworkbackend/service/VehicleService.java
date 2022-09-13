package com.upwork.upworkbackend.service;

import com.upwork.upworkbackend.model.Vehicle;
import com.upwork.upworkbackend.model.VehicleWorkingStatus;
import com.upwork.upworkbackend.model.WorkingStatus;
import com.upwork.upworkbackend.payload.request.VehicleSaveRequest;
import com.upwork.upworkbackend.repository.VehicleRepository;
import com.upwork.upworkbackend.repository.VehicleStatusRepository;
import com.upwork.upworkbackend.repository.WorkingStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    private final VehicleStatusRepository vehicleStatusRepository;

    private final WorkingStatusRepository workingStatusRepository;


    public Vehicle getVehicleById(Long vehicleId){
        return vehicleRepository.findById(vehicleId).orElseThrow(()-> new IllegalStateException("Vehicle not found"));
    }


    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    public List<Vehicle> getAllVehiclesByMonth(Month month){
       List<VehicleWorkingStatus> vehicleWorkingStatus = vehicleStatusRepository.findAllByMonth(month);
       return vehicleWorkingStatus.stream().map(VehicleWorkingStatus::getVehicle).collect(Collectors.toList());
    }

    public void editVehicleStatus(Long vehicleStatusId,Vehicle vehicleStatus) {
        VehicleWorkingStatus vehicleWorkingStatus = vehicleStatusRepository.findById(vehicleStatusId).orElseThrow(()->new IllegalStateException("Wrong vehicle status id"));
        Vehicle vehicle = vehicleWorkingStatus.getVehicle();
        vehicle.setWorkingStatusList(vehicleStatus.getWorkingStatusList());
        vehicleRepository.flush();

    }

    public void saveVehicle(VehicleSaveRequest vehicleSaveRequest) {
        Vehicle vehicle = Vehicle.builder()
                .fleetNo(vehicleSaveRequest.getFleetNo())
                .vehicleModel(vehicleSaveRequest.getVehicleModel())
                .operator(vehicleSaveRequest.getOperator())
                .size(vehicleSaveRequest.getSize())
                .build();


        WorkingStatus workingStatus = workingStatusRepository.findById(1L).orElseThrow(()->new IllegalStateException("There is no status with id: " + 1L));

        vehicleSaveRequest.setVehicleStatus(workingStatus,vehicle);
        System.out.println(vehicleSaveRequest.getVehicleWorkingStatuses().size());
        vehicle.setWorkingStatusList(vehicleSaveRequest.getVehicleWorkingStatuses());

        vehicleRepository.save(vehicle);



    }
}
