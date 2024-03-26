package com.example.carrental.dao;

import com.example.carrental.entity.LocationEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class LocationDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public List<LocationEntity> getAllLocation() {
        return entityManager.createQuery("SELECT l from LocationEntity l",LocationEntity.class).getResultList();
    }
@Transactional
    public  void save() {
    Session s= entityManager.unwrap(Session.class);
    LocationEntity l= new LocationEntity("orai","ram");
    LocationEntity l2= new LocationEntity("orai2","ras");
    LocationEntity l3= new LocationEntity("orai","ara");
    s.save(l);
    s.save(l2);
    s.save(l3);
    }

    public void saveLocation(LocationEntity locationEntity) {
        Session s= entityManager.unwrap(Session.class);
        s.save(locationEntity);
    }
}
