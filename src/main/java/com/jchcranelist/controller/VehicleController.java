package com.jchcranelist.controller;

import com.jchcranelist.model.Vehicle;
import com.jchcranelist.service.VehicleService;
import com.jchcranelist.payload.request.VehicleSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;



    @GetMapping
    public List<Vehicle> getVehiclesByMonth(@RequestParam("date") String date){
        return  vehicleService.getAllVehiclesByMonth(date);
    }

    @PutMapping("/edit")
    public void editVehicleStatus(@RequestBody Vehicle vehicle){
        vehicleService.editVehicleStatus(vehicle);
    }

    @PostMapping("/save")
    public void saveVehicle(@RequestBody VehicleSaveRequest vehicleSaveRequest){
        vehicleService.saveVehicle(vehicleSaveRequest);
    }


    @DeleteMapping("/delete/id/{vehicleId}")
    public void deleteVehicle(@PathVariable Long vehicleId){
        vehicleService.deleteVehicle(vehicleId);
    }
}
