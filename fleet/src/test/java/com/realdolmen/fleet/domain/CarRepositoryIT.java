package com.realdolmen.fleet.domain;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.realdolmen.fleet.SpringBootTransactionalIntegrationTest;
import com.realdolmen.fleet.repositories.CarRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;


@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@DatabaseSetup(CarRepositoryIT.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL , value = { CarRepositoryIT.DATASET })
@DirtiesContext
@SpringBootTransactionalIntegrationTest
public class CarRepositoryIT {

    protected static final String DATASET = "classpath:datasets/it-cars.xml";

    @Autowired
    private CarRepository repository;

    @Test
    public void findAllShouldReturnOneCar() {
        //Assert.assertEquals (2,repository.findAll().size());
    }

}
