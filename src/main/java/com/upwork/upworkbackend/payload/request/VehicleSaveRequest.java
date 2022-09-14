package com.upwork.upworkbackend.payload.request;

import com.upwork.upworkbackend.model.Vehicle;
import com.upwork.upworkbackend.model.VehicleWorkingStatus;
import com.upwork.upworkbackend.model.WorkingStatus;
import lombok.*;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
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
    private LocalDate date;
    private List<VehicleWorkingStatus> vehicleWorkingStatuses = new ArrayList<>();

    public void setVehicleStatus(WorkingStatus workingStatus, Vehicle vehicle){
        YearMonth yearMonthObject = YearMonth.of(date.getYear(),date.getMonth());
        int days = yearMonthObject.lengthOfMonth();

        for (int i = 0; i < days; i++){
            VehicleWorkingStatus vehicleWorkingStatus = VehicleWorkingStatus.builder()
                    .vehicle(vehicle)
                    .date(date)
                    .month(date.getMonth())
                    .day(i)
                    .workingStatus(workingStatus)
                    .build();
            this.getVehicleWorkingStatuses().add(vehicleWorkingStatus);
        }
    }
}
