package com.realdolmen.fleet.repository;

import com.realdolmen.fleet.SpringBootTransactionalIntegrationTest;
import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.FunctionalLevel;
import com.realdolmen.fleet.mother.CarMother;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTransactionalIntegrationTest
public class CarRepositoryIntegrationTest {

    private Car activeCar, activeCarTwo, inactiveCar, inactiveCarTwo;
    private List<Car> activeCars;
    private List<Car> inactiveCars;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private FunctionalLevelRepository functionalLevelRepository;

    @Before
    public void init() {
        FunctionalLevel category = new FunctionalLevel();
        category.setFLevel(1);
        functionalLevelRepository.save(category);

        activeCars = Arrays.asList(activeCar, activeCarTwo);
        activeCars.forEach(car -> {
            car = CarMother.init().build();
            car.setCategory(category);
            car.setActive(true);
            carRepository.save(car);
        });

        inactiveCars = Arrays.asList(inactiveCar, inactiveCarTwo);
        inactiveCars.forEach(car -> {
            car = CarMother.init().build();
            car.setCategory(category);
            car.setActive(false);
            carRepository.save(car);
        });

    }

    @Test
    public void findAllShouldReturnOneCar() {
        List<Car> result = carRepository.findAll();
        assertEquals(4, result.size());
    }

    @Test
    public void findByActiveShouldOnlyReturnActiveCars() {
        List<Car> resultActiveCars = carRepository.findByActive(true);
        assertTrue(resultActiveCars.size() == 2);
        resultActiveCars.forEach(car -> assertTrue(car.getActive()));
    }


    @Test
    public void findByActiveShouldOnlyReturnInactiveCars() {
        List<Car> resultActiveCars = carRepository.findByActive(false);
        assertTrue(resultActiveCars.size() == 2);
        resultActiveCars.forEach(car -> assertFalse(car.getActive()));
    }

}
