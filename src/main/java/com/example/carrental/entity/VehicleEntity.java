package com.example.carrental.entity;

import com.example.carrental.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="vehicle_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VehicleEntity {
    @Id
    @Column(name = "vehicleId")
    private String vehicleId;
    @Column(name = "type")
    private VehicleType type;
    @Column(name = "owner")
    private String owner;
    @Column(name = "location")
    private String location;
    @Column(name = "store")
    private String store;
    @Column(name="available")
    private boolean isAvailable;

}
