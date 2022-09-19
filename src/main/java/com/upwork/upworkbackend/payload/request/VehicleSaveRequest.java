package com.upwork.upworkbackend.payload.request;

import com.upwork.upworkbackend.model.Vehicle;
import com.upwork.upworkbackend.model.VehicleWorkingStatus;
import com.upwork.upworkbackend.model.WorkingStatus;
import lombok.*;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleSaveRequest {


    private String vehicleModel;
    private String fleetNo;
    private String operator;
    private Double size;
    private String monthYear;
    private List<VehicleWorkingStatus> vehicleWorkingStatuses = new ArrayList<>();

    public void setVehicleStatus(WorkingStatus workingStatus, Vehicle vehicle){
        String monthStr = monthYear.split("-")[0];
        String yearStr = monthYear.split("-")[1];
        YearMonth yearMonth = YearMonth.of(Integer.parseInt(yearStr),Integer.parseInt(monthStr));
        int days = yearMonth.lengthOfMonth();

        for (int i = 0; i < days; i++){
            VehicleWorkingStatus vehicleWorkingStatus = VehicleWorkingStatus.builder()
                    .vehicle(vehicle)
                    .day(i)
                    .workingStatus(workingStatus)
                    .build();
            this.getVehicleWorkingStatuses().add(vehicleWorkingStatus);
        }
    }
}
