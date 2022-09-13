package com.upwork.upworkbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vehicleModel;
    private String fleetNo;
    private String operator;
    private Double size;
    @OneToMany(cascade = CascadeType.ALL)
    private List<VehicleWorkingStatus> workingStatusList = new ArrayList<>();
}
