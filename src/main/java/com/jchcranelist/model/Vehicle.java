package com.jchcranelist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vehicleModel;
    private String fleetNo;
    private String operator;
    private Month month;
    private Year year;
    private Double size;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "vehicle")
    @OrderBy("id")
    private List<VehicleWorkingStatus> workingStatusList = new ArrayList<>();
}
