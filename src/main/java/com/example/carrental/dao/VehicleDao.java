package com.example.carrental.dao;

import com.example.carrental.model.Vehicle;
import com.example.carrental.entity.VehicleEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class VehicleDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void saveVehicle(Vehicle vehicle) {
        Session s= entityManager.unwrap(Session.class);
        s.save(vehicle.toVehicleEntity());
    }

    @Transactional
    public List<VehicleEntity> getAvailableVehicle(String location, String store) {
        return entityManager.createQuery("SELECT v from VehicleEntity v WHERE v.location= :location and v.store= :store and v.isAvailable= :available",VehicleEntity.class).setParameter("location",location).setParameter("store",store).setParameter("available",true).getResultList();
    }
}
