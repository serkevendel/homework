/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entityhf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vendel
 */
@Entity
@NamedQuery(name = "QueryForMalePerson", query = "SELECT p FROM Person p WHERE p.gender=com.mycompany.entityhf.Gender.MALE")
public class Person implements Serializable {
    @Id @GeneratedValue
    protected Long id;
    
    @Enumerated(EnumType.STRING)
    protected Gender gender;
    
    @Temporal(TemporalType.DATE)
    protected Date birthDate;
    @OneToMany
    @JoinColumn(name="person_fk")
    protected List<Vehicle> vehicles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", gender=" + gender + ", birthDate=" + birthDate + ", vehicles=" + vehicles + '}';
    }
    
    
}
