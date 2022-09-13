package com.upwork.upworkbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Month;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleWorkingStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @ManyToOne
    private WorkingStatus workingStatus;
    private Month month;
    private int day;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Vehicle vehicle;
}

