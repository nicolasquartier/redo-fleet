package com.realdolmen.fleet.mother;


import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.FunctionalLevel;
import com.realdolmen.fleet.domain.enums.Brand;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Date;

public class CarMother {

    Car car;

    public CarMother() {
        FunctionalLevel level = new FunctionalLevel();
        this.car = new Car();
        car.setActive(true);
        car.setBrand(Brand.AUDI);
        car.setEmission(100);
        car.setCategory(level);
        car.setEmission(100);
        car.setFiscalHorsePower(10);
        car.setHybrid(false);
        car.setProductionDate(new Date());
    }

    public void setCategoryOrFunctionLevel(FunctionalLevel category) {
        this.car.setCategory(category);}

    public static CarMother init() {
        return new CarMother();
    }

    public Car build() {
        return this.car;
    }
}
