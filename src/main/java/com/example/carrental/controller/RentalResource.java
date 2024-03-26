package com.example.carrental.controller;

import com.example.carrental.VehicleType;
import com.example.carrental.dao.LocationDao;
import com.example.carrental.dao.RentalDao;
import com.example.carrental.dao.UserDao;
import com.example.carrental.dao.VehicleDao;
import com.example.carrental.entity.LocationEntity;
import com.example.carrental.entity.RentalEntity;
import com.example.carrental.entity.UserEntity;
import com.example.carrental.model.Vehicle;
import com.example.carrental.payment.FourWheelerPayment;
import com.example.carrental.payment.Payment;
import com.example.carrental.payment.TwoWheelerPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class RentalResource {

    @Autowired
    private LocationDao locationDao;

    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private RentalDao rentalDao;

    @Autowired
    private UserDao userDao;

    @GetMapping("v1/location")
    public ResponseEntity<Object> getAllLocation(){
        return ResponseEntity.ok(locationDao.getAllLocation().stream().map(LocationEntity::getLocation).distinct());
    }

    @PostMapping("v1/location")
    public ResponseEntity<Object> registerAStore(@RequestBody LocationEntity locationEntity){
        locationDao.saveLocation(locationEntity);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @GetMapping("v1/location/{location}")
    public ResponseEntity<Object> getAllStore(@PathVariable String location){
        return ResponseEntity.ok(locationDao.getAllLocation().stream().filter(p->p.getLocation().equalsIgnoreCase(location)).toList());
    }

    @GetMapping("v1/vehicles/{location}/{store}")
    public ResponseEntity<Object> getAllVehicles(@PathVariable String location,@PathVariable String store){
        return ResponseEntity.ok(vehicleDao.getAvailableVehicle(location,store));
    }

    @PostMapping("v1/vehicle")
    public ResponseEntity<Object> createVehicle(@RequestBody Vehicle vehicle){
        vehicleDao.saveVehicle(vehicle);
        return ResponseEntity.ok("Done");
    }

    @PostMapping("v1/rent/{user}/{vehicle}")
    public RentalEntity rentVehicle(@PathVariable String user, @PathVariable String vehicle) throws Exception {
        return rentalDao.rentAVehicle(user,vehicle);
    }

    @PutMapping("v1/rent/{id}")
    public Payment unRentVehicle(@PathVariable String id) throws Exception {
        RentalEntity rentalEntity=rentalDao.unrentAVehicle(id);
        return toBill(rentalEntity);
    }

    @PostMapping("v1/user")
    public ResponseEntity<Object> addUser(@RequestBody UserEntity userEntity){
        userDao.addUser(userEntity);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private Payment toBill(RentalEntity rentalEntity) {
        Payment payment= getInstance(rentalEntity.getType()).calculateCost(rentalEntity.getEndTime(),rentalEntity.getStartTime());
        payment.setUser(rentalEntity.getUserId());
        payment.setTicketId(rentalEntity.getId());
        payment.setUser(rentalEntity.getUserId());
        payment.setIssueDate(new Date(rentalEntity.getStartTime()));
        payment.setEndTime(new Date(rentalEntity.getEndTime()));
        return payment;
    }

    private Payment getInstance(VehicleType type) {
        if(type.name().equalsIgnoreCase(VehicleType.TWO_WHEELER.name())){
            return new TwoWheelerPayment();
        }
        return new FourWheelerPayment();
    }

    @PostMapping("v1/prerequisite")
    public String save(){
        locationDao.save();
        vehicleDao.saveVehicle(new Vehicle("1",VehicleType.FOUR_WHEELER,"me","orai","ram"));
        return "done";
    }

}
