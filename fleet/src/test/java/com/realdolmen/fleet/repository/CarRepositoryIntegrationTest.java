package com.realdolmen.fleet.repository;

import com.realdolmen.fleet.SpringBootTransactionalIntegrationTest;
import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.FunctionalLevel;
import com.realdolmen.fleet.domain.enums.Brand;
import com.realdolmen.fleet.domain.enums.CarType;
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

    @Test
    public void findFilteredCarsByBrand() {
        FunctionalLevel category = functionalLevelRepository.findByFLevel(1);
        List<Car> filteredCarsFindByBrand = carRepository.findByBrand(Brand.AUDI);
        assertTrue(filteredCarsFindByBrand.size() == 4);
        filteredCarsFindByBrand.forEach(car -> {
            assertEquals(car.getBrand(), Brand.AUDI);
        });
    }

    @Test
    public void findFIlteredCarsByType() {
        FunctionalLevel category = functionalLevelRepository.findByFLevel(1);
        List<Car> filteredCarsFindByType = carRepository.findByType(CarType.BERLINE);
        assertTrue(filteredCarsFindByType.size() == 4);
        filteredCarsFindByType.forEach(car -> {
            assertEquals(car.getType(), CarType.BERLINE);
        });
    }

    @Test
    public void findFilterecCarsByCategory() {
        FunctionalLevel category = functionalLevelRepository.findByFLevel(1);
        List<Car> filteredCarsFindByCategory = carRepository.findByCategory(category);
        assertTrue(filteredCarsFindByCategory.size() == 4);
        filteredCarsFindByCategory.forEach(car -> {
            assertEquals(car.getCategory(), category);
        });
    }

    @Test
    public void findFilteredCarsByCategoryAndBrand() {
        FunctionalLevel category = functionalLevelRepository.findByFLevel(1);
        List<Car> filteredCarsFindByCategoryAndBrand = carRepository.findByCategoryAndBrand(category, Brand.AUDI);
        assertTrue(filteredCarsFindByCategoryAndBrand.size() == 4);
        filteredCarsFindByCategoryAndBrand.forEach(car -> {
            assertEquals(car.getCategory(), category);
            assertEquals(car.getBrand(), Brand.AUDI);
        });
    }

    @Test
    public void findFilteredCarsByCategoryAndType() {
        FunctionalLevel category = functionalLevelRepository.findByFLevel(1);
        List<Car> filteredCarsFindByCategoryAndType = carRepository.findByCategoryAndType(category, CarType.BERLINE);
        assertTrue(filteredCarsFindByCategoryAndType.size() == 4);
        filteredCarsFindByCategoryAndType.forEach(car -> {
            assertEquals(car.getCategory(), category);
            assertEquals(car.getType(), CarType.BERLINE);
        });
    }

    @Test
    public void findFilteredCarsFindByTypeAndBrand() {
        FunctionalLevel category = functionalLevelRepository.findByFLevel(1);
        List<Car> filteredCarsFindByTypeAndBrand = carRepository.findByTypeAndBrand(CarType.BERLINE, Brand.AUDI);
        assertTrue(filteredCarsFindByTypeAndBrand.size() == 4);
        filteredCarsFindByTypeAndBrand.forEach(car -> {
            assertEquals(car.getType(), CarType.BERLINE);
            assertEquals(car.getBrand(), Brand.AUDI);
        });
    }

    @Test
    public void findFilteredCarsByCategoryAndTypeAndBrand() {
        FunctionalLevel category = functionalLevelRepository.findByFLevel(1);
        List<Car> filteredCarsFindByCategoryAndTypeAndBrand = carRepository.findByCategoryAndTypeAndBrand(category, CarType.BERLINE, Brand.AUDI);

        assertTrue(filteredCarsFindByCategoryAndTypeAndBrand.size() == 4);
        filteredCarsFindByCategoryAndTypeAndBrand.forEach(car -> {
            assertEquals(car.getCategory(), category);
            assertEquals(car.getType(), CarType.BERLINE);
            assertEquals(car.getBrand(), Brand.AUDI);
        });
    }

}
