package com.jchcranelist.service;

import com.jchcranelist.model.Vehicle;
import com.jchcranelist.repository.VehicleRepository;
import com.jchcranelist.model.VehicleWorkingStatus;
import com.jchcranelist.model.WorkingStatus;
import com.jchcranelist.payload.request.VehicleSaveRequest;
import com.jchcranelist.repository.VehicleStatusRepository;
import com.jchcranelist.repository.WorkingStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class VehicleService {
    private final VehicleRepository vehicleRepository;


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
            WorkingStatus status0 = new WorkingStatus("0","#FFFFFF",false);
            WorkingStatus statusJO = new WorkingStatus("JO","#f3a30f",true);
            WorkingStatus statusAV = new WorkingStatus("AV","#add8e6",false);
            WorkingStatus statusp9O = new WorkingStatus("P90","#4646d7",true);
            WorkingStatus statusLT = new WorkingStatus("LT","#76d376",true);
            WorkingStatus statusQT = new WorkingStatus("QT","#ffb6c1",true);
            WorkingStatus statuspP50 = new WorkingStatus("P50","#add8e6",true);
            WorkingStatus statusP75 = new WorkingStatus("P75","#20b2aa",true);
            WorkingStatus statusSE = new WorkingStatus("SE","#fdffa2",true);
            WorkingStatus statusBD =new WorkingStatus("BD","#ed3333",false);




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
