package com.upwork.upworkbackend.controller;

import com.upwork.upworkbackend.model.Vehicle;
import com.upwork.upworkbackend.payload.request.VehicleSaveRequest;
import com.upwork.upworkbackend.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
@CrossOrigin
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;



    @GetMapping
    public List<Vehicle> getVehiclesByMonth(@RequestParam Month month){
        return  vehicleService.getAllVehiclesByMonth(month);
    }

    @PutMapping("/edit")
    public void editVehicleStatus(@RequestBody Vehicle vehicle){
        vehicleService.editVehicleStatus(vehicle);
    }

    @PostMapping("/save")
    public void saveVehicle(@RequestBody VehicleSaveRequest vehicleSaveRequest){
        vehicleService.saveVehicle(vehicleSaveRequest);
    }
}
