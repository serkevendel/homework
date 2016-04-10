/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entityhf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vendel
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class VehicleSuper {
    @Id @GeneratedValue
    protected Long id;
    
    @Temporal(TemporalType.DATE)
    protected Date productionDate;
    
    @ElementCollection
    @CollectionTable(name="Available colors")
    protected List<ColorType> availableColors = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public List<ColorType> getAvailableColors() {
        return availableColors;
    }

    public void setAvailableColors(List<ColorType> availableColors) {
        this.availableColors = availableColors;
    }
    
    
}
