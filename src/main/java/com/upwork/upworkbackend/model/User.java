package com.upwork.upworkbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 1)
    private String name;
    @Size(min = 1)
    private String surname;
    private String email;
    private String phoneNumber;
    @Size(min = 8)
    private String password;
    private Role role;
}
