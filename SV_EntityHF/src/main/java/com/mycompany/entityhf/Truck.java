/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entityhf;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;

/**
 *
 * @author vendel
 */
@Entity
@NamedQuery(name = "QueryForManTruck", query = "SELECT t FROM Truck t WHERE t.truckType=com.mycompany.entityhf.TruckType.MAN")
public class Truck extends Vehicle implements Serializable{
    
    @Enumerated(EnumType.STRING)
    protected TruckType truckType;

    public TruckType getTruckType() {
        return truckType;
    }

    public void setTruckType(TruckType truckType) {
        this.truckType = truckType;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Date getProductionDate() {
        return productionDate;
    }

    @Override
    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    @Override
    public List<ColorType> getAvailableColors() {
        return availableColors;
    }

    @Override
    public void setAvailableColors(List<ColorType> availableColors) {
        this.availableColors = availableColors;
    }

    @Override
    public String toString() {
        return "Truck{" + "truckType=" + truckType + '}';
    }
    
    
}
