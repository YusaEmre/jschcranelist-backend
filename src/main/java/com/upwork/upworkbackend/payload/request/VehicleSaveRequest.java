package com.upwork.upworkbackend.payload.request;

import com.upwork.upworkbackend.model.Vehicle;
import com.upwork.upworkbackend.model.VehicleWorkingStatus;
import com.upwork.upworkbackend.model.WorkingStatus;
import lombok.*;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
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
        Calendar calendar = Calendar.getInstance();
        System.out.println(date);
        Month month = this.date.getMonth();
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < days; i++){
            System.out.println(i);
            VehicleWorkingStatus vehicleWorkingStatus = VehicleWorkingStatus.builder()
                    .vehicle(vehicle)
                    .date(date)
                    .month(month)
                    .day(i)
                    .workingStatus(workingStatus)
                    .build();
            this.getVehicleWorkingStatuses().add(vehicleWorkingStatus);
        }
    }
}
