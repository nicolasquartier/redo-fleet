package com.realdolmen.fleet.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.realdolmen.fleet.SpringBootTransactionalIntegrationTest;
import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.FunctionalLevel;
import com.realdolmen.fleet.mother.CarMother;
import com.realdolmen.fleet.repositories.CarRepository;
import com.realdolmen.fleet.repositories.FunctionalLevelRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTransactionalIntegrationTest
public class CarRepositoryIntegrationTest {

    private Car car;
    private Car carTwo;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private FunctionalLevelRepository functionalLevelRepository;

    @Before
    public void init() {
        FunctionalLevel category = new FunctionalLevel();
        category.setFLevel(1);
        functionalLevelRepository.save(category);

        this.car = CarMother.init().setCategoryOrFunctionLevel(category).build();
        carRepository.save(car);

        this.carTwo = CarMother.init().setCategoryOrFunctionLevel(category).build();
        carRepository.save(carTwo);

    }

    @Test
    public void shouldReturnCarId() {
        assertNotNull(this.car.getId());
        assertEquals(new Long(1L), this.car.getId());
    }

    @Test
    public void findAllShouldReturnOneCar() {
        List<Car> result = carRepository.findAll();

        assertTrue(result.contains(car));
        assertTrue(result.contains(carTwo));

        assertEquals(2, result.size());
    }

}
