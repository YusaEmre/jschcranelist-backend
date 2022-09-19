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
import java.time.Year;
import java.util.ArrayList;
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

    public List<Vehicle> getAllVehiclesByMonth(String date) {
        String[] dateArray = date.split("-");
        Month month = Month.of(Integer.parseInt(dateArray[0]));
        Year year = Year.of(Integer.parseInt(dateArray[1]));
        return vehicleRepository.findAllByMonthAndYear(month,year);
    }

    public void editVehicleStatus(Vehicle requestVehicle) {
        for (VehicleWorkingStatus vehicleWorkingStatus:requestVehicle.getWorkingStatusList()) {
            vehicleWorkingStatus.setVehicle(requestVehicle);
        }
        vehicleRepository.saveAndFlush(requestVehicle);

    }

    public void saveVehicle(VehicleSaveRequest vehicleSaveRequest) {
        String[] dateArray = vehicleSaveRequest.getMonthYear().split("-");
        Month month = Month.of(Integer.parseInt(dateArray[0]));
        Year year = Year.of(Integer.parseInt(dateArray[1]));
        Vehicle vehicle = Vehicle.builder()
                .fleetNo(vehicleSaveRequest.getFleetNo())
                .vehicleModel(vehicleSaveRequest.getVehicleModel())
                .operator(vehicleSaveRequest.getOperator())
                .month(month)
                .year(year)
                .size(vehicleSaveRequest.getSize())
                .build();

        if (workingStatusRepository.findAll().size() > 0) {
            WorkingStatus workingStatus = workingStatusRepository.findById(1L).orElseThrow(()->new IllegalStateException("There is no status with id: " + 1L));
            vehicleSaveRequest.setVehicleStatus(workingStatus,vehicle);

        }else {

            workingStatusRepository.save(new WorkingStatus("0"));
            workingStatusRepository.save(new WorkingStatus("JO"));
            workingStatusRepository.save(new WorkingStatus("AV"));
            workingStatusRepository.save(new WorkingStatus("P90"));
            workingStatusRepository.save(new WorkingStatus("LT"));
            workingStatusRepository.save(new WorkingStatus("QT"));
            workingStatusRepository.save(new WorkingStatus("P50"));
            workingStatusRepository.save(new WorkingStatus("P75"));
            workingStatusRepository.save(new WorkingStatus("SE"));
            workingStatusRepository.save(new WorkingStatus("BD"));
            WorkingStatus workingStatus = workingStatusRepository.findById(1L).orElseThrow(()->new IllegalStateException("There is no status with id: " + 1L));
            vehicleSaveRequest.setVehicleStatus(workingStatus,vehicle);
        }

        vehicle.setWorkingStatusList(vehicleSaveRequest.getVehicleWorkingStatuses());

        vehicleRepository.save(vehicle);



    }
}
