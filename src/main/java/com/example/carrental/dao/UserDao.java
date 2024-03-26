package com.example.carrental.dao;

import com.example.carrental.entity.UserEntity;
import com.example.carrental.model.Vehicle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void addUser(UserEntity user) {
        Session s = entityManager.unwrap(Session.class);
        s.merge(user);
    }
}

