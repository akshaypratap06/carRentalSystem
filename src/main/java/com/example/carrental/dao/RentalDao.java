package com.example.carrental.dao;

import com.example.carrental.entity.RentalEntity;
import com.example.carrental.entity.VehicleEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public class RentalDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public RentalEntity rentAVehicle(String user, String vehicle) throws Exception {
        Session s= entityManager.unwrap(Session.class);
        VehicleEntity vehicle1= s.get(VehicleEntity.class,vehicle);
        if(!vehicle1.isAvailable())
            throw new Exception("Not available");
        RentalEntity rentalEntity= new RentalEntity();
        rentalEntity.setVehicleId(vehicle);
        rentalEntity.setStartTime(new Date().getTime());
        rentalEntity.setUserId(user);
        s.save(rentalEntity);
        vehicle1.setAvailable(false);
        s.merge(vehicle1);
        return rentalEntity;
    }
    @Transactional
    public RentalEntity unrentAVehicle(String id) {
        Session s= entityManager.unwrap(Session.class);
        RentalEntity rentalEntity= entityManager.createQuery("select r from RentalEntity r where r.id= :id",RentalEntity.class).setParameter("id",id).getSingleResult();
        rentalEntity.setEndTime(new Date().getTime());
        s.merge(rentalEntity);
        VehicleEntity vehicle1= s.get(VehicleEntity.class,rentalEntity.getVehicleId());
        vehicle1.setAvailable(true);
        s.merge(vehicle1);
        return rentalEntity;
    }
}
