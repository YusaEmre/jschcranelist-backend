package com.jchcranelist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class VehicleWorkingStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private WorkingStatus workingStatus;
    private int day;
    private String comment = "";
    @ManyToOne (cascade = CascadeType.ALL)
    @JsonIgnore
    private Vehicle vehicle;
}

