package com.realdolmen.fleet.domain;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.realdolmen.fleet.SpringBootTransactionalIntegrationTest;
import com.realdolmen.fleet.mother.CarMother;
import com.realdolmen.fleet.mother.FunctionalLevelMother;
import com.realdolmen.fleet.repositories.CarRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
//@DatabaseSetup(CarRepositoryIT.DATASET)
//@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = {CarRepositoryIT.DATASET})
@DirtiesContext
@SpringBootTransactionalIntegrationTest
public class CarRepositoryIT {

    //    protected static final String DATASET = "classpath:datasets/it-cars.xml";
    private Car car;

    @Autowired
    private CarRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Before
    public void init() {
        FunctionalLevel functionalLevel = FunctionalLevelMother.init().build();
        functionalLevel.setFLevel(1);
        entityManager.persist(functionalLevel);

        CarMother carMother = CarMother.init();
        carMother.setCategoryOrFunctionLevel(functionalLevel);
        this.car = carMother.build();
    }

    @Test
    public void shouldReturnCarId() {
        entityManager.persist(this.car);
        assertNotNull(this.car.getId());
        assertEquals(new Long(1L), this.car.getId());
    }

    @Test
    @Ignore
    public void findAllShouldReturnOneCar() {
        assertEquals(2, repository.findAll().size());
    }

}
