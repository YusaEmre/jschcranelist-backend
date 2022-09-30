package com.jchcranelist.service;

import com.jchcranelist.model.Vehicle;
import com.jchcranelist.repository.VehicleRepository;
import com.jchcranelist.model.VehicleWorkingStatus;
import com.jchcranelist.model.WorkingStatus;
import com.jchcranelist.payload.request.VehicleSaveRequest;
import com.jchcranelist.repository.VehicleStatusRepository;
import com.jchcranelist.repository.WorkingStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            WorkingStatus status0 = new WorkingStatus("0","#FFFFFF");
            WorkingStatus statusJO = new WorkingStatus("JO","#f3a30f");
            WorkingStatus statusAV = new WorkingStatus("AV","#add8e6");
            WorkingStatus statusp9O = new WorkingStatus("P90","#4646d7");
            WorkingStatus statusLT = new WorkingStatus("LT","#76d376");
            WorkingStatus statusQT = new WorkingStatus("QT","#ffb6c1");
            WorkingStatus statuspP50 = new WorkingStatus("P50","#add8e6");
            WorkingStatus statusP75 = new WorkingStatus("P75","#20b2aa");
            WorkingStatus statusSE = new WorkingStatus("SE","#fdffa2");
            WorkingStatus statusBD =new WorkingStatus("BD","#ed3333");


            System.out.println(statusBD.getColor());
            System.out.println(statusJO.getColor());
            System.out.println(status0.getColor());


            List<WorkingStatus> workingStatusList = Arrays.asList(status0,statusBD,statusAV,statusJO,statusP75,statusp9O,statusLT,statusQT,statuspP50,statusSE);
            workingStatusRepository.saveAll(workingStatusList);
            WorkingStatus workingStatus = workingStatusRepository.findById(1L).orElseThrow(()->new IllegalStateException("There is no status with id: " + 1L));
            vehicleSaveRequest.setVehicleStatus(workingStatus,vehicle);
        }

        vehicle.setWorkingStatusList(vehicleSaveRequest.getVehicleWorkingStatuses());

        vehicleRepository.save(vehicle);



    }


    public void deleteVehicle(Long vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }
}
