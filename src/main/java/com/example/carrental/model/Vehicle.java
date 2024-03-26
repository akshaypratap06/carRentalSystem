package com.example.carrental.model;

import com.example.carrental.VehicleType;
import com.example.carrental.entity.VehicleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vehicle {
    private String vehicleId;
    private VehicleType type;
    private String owner;
    private String location;
    private String store;

    public VehicleEntity toVehicleEntity(){
        VehicleEntity v= new VehicleEntity();
        v.setVehicleId(this.getVehicleId());
        v.setType(this.getType());
        v.setOwner(this.getOwner());
        v.setLocation(this.getLocation());
        v.setStore(this.getStore());
        v.setAvailable(true);
        return v;
    }
}
