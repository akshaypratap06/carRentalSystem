package com.example.carrental.entity;

import com.example.carrental.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rental_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RentalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "vehicle_id")
    private String vehicleId;

    @Column(name="type")
    private VehicleType type;

    @Column(name = "user_id")
    private String userId;

    @Column(name="start_time")
    private long startTime;

    @Column(name="end_time")
    private long endTime;

}
