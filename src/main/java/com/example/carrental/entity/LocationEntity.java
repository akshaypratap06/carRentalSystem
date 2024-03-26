package com.example.carrental.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "location_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationEntity {

    @Id
    @Column(name = "location")
    private String location;

    @Id
    @Column(name="store")
    private String store;

}
