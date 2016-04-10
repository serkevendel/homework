/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entityhf;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author vendel
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger("logger");
    private Main(){}
    
    public static void main(String[] args) {
        
        
        Car audicar = new Car();
        audicar.setCartype(CarType.AUDI);
        audicar.setProductionDate(new Date());
        audicar.getAvailableColors().add(ColorType.RED);
        audicar.getAvailableColors().add(ColorType.BLACK);
        
        Car bmwcar = new Car();
        bmwcar.setCartype(CarType.BMW);
        bmwcar.setProductionDate(new Date());
        bmwcar.getAvailableColors().add(ColorType.WHITE);
        bmwcar.getAvailableColors().add(ColorType.BLACK);
        
        Car mazdacar = new Car();
        mazdacar.setCartype(CarType.MAZDA);
        mazdacar.setProductionDate(new Date());
        mazdacar.getAvailableColors().add(ColorType.WHITE);
        mazdacar.getAvailableColors().add(ColorType.BLACK);
        mazdacar.getAvailableColors().add(ColorType.GREEN);
        
        Truck mantruck = new Truck();
        mantruck.setTruckType(TruckType.MAN);
        mantruck.setProductionDate(new Date());
        mantruck.getAvailableColors().add(ColorType.GREY);
        mantruck.getAvailableColors().add(ColorType.YELLOW);
        
        Bike speedbike = new Bike();
        speedbike.setBikeType(BikeType.SPEED);
        speedbike.setProductionDate(new Date());
        speedbike.getAvailableColors().add(ColorType.RED);
        
        Bike tourerbike = new Bike();
        tourerbike.setBikeType(BikeType.TOURER);
        tourerbike.setProductionDate(new Date());
        tourerbike.getAvailableColors().add(ColorType.BLUE);
        
        Person carowner = new Person();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -20);
        Date carOwnerBirthDate = cal.getTime();
        carowner.setBirthDate(carOwnerBirthDate);
        carowner.setGender(Gender.MALE);
        
        Person bikeowner = new Person();
         cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -25);
        Date bikeOwnerBirthDate = cal.getTime();
        bikeowner.setBirthDate(bikeOwnerBirthDate);
        bikeowner.setGender(Gender.FEMALE);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PUNIT");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(audicar);
        em.persist(bmwcar);
        em.persist(mazdacar);
        em.persist(mantruck);
        em.persist(speedbike);
        em.persist(tourerbike);
        em.persist(carowner);
        em.persist(bikeowner);
        
        List<Vehicle> bikes = new ArrayList<>();
        bikes.add(speedbike);
        bikes.add(tourerbike);
        bikeowner.setVehicles(bikes);
        
        List<Vehicle> cars = new ArrayList<>();
        cars.add(bmwcar);
        cars.add(audicar);
        cars.add(mazdacar);
        carowner.setVehicles(cars);
        tx.commit();
        
        
        
        TypedQuery<Truck> truckq = em.createNamedQuery("QueryForManTruck", Truck.class);
        Truck testQueryTruck = truckq.getSingleResult();
        
        TypedQuery<Car> bmwcarq = em.createNamedQuery("QueryForBmwCar", Car.class);
        Car testQueryCarBmw = bmwcarq.getSingleResult();
        
        TypedQuery<Car> audicarq = em.createNamedQuery("QueryForAudiCar", Car.class);
        Car testQueryCarAudi = audicarq.getSingleResult();
        
        TypedQuery<Person> personq = em.createNamedQuery("QueryForMalePerson", Person.class);
        Person testQueryPerson = personq.getSingleResult();
        
        TypedQuery<Bike> bikeq = em.createNamedQuery("QueryForSpeedBike", Bike.class);
        Bike testQueryBike = bikeq.getSingleResult();
        LOGGER.info("-----------------------TESTING QUERIES-------------------------");
        LOGGER.info(testQueryBike.toString());
        LOGGER.info(testQueryCarBmw.toString());
        LOGGER.info(testQueryCarAudi.toString());
        LOGGER.info(testQueryPerson.toString());
        LOGGER.info(testQueryTruck.toString());
                
                
        em.close();
        emf.close();
    }
    
}
