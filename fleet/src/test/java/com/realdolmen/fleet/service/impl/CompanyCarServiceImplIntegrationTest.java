package com.realdolmen.fleet.service.impl;

import com.realdolmen.fleet.SpringBootTransactionalIntegrationTest;
import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.CompanyCar;
import com.realdolmen.fleet.domain.Option;
import com.realdolmen.fleet.mother.CarMother;
import com.realdolmen.fleet.mother.CompanyCarMother;
import com.realdolmen.fleet.mother.OptionMother;
import com.realdolmen.fleet.repository.CarRepository;
import com.realdolmen.fleet.repository.CompanyCarRepository;
import com.realdolmen.fleet.repository.OptionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTransactionalIntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyCarServiceImplIntegrationTest {

    @Autowired
    private CompanyCarServiceImpl companyCarService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private CompanyCarRepository companyCarRepository;

    private Car car;
    private List<Option> options;
    private CompanyCar companyCar;

    @Before
    public void init() {
        initCar();
        initOptions();
        initCompanyCar();
    }

    @Test
    public void createCompanyCarWithOptionsCreatesACarWithOptions() {
        companyCarService.createCompanyCarWithOptions(companyCar, car, options);

        CompanyCar result = companyCarRepository.findOne(1L);

        assertSame(car, result.getCar());
        assertSame(result.getCar(), result.getCar());

        assertTrue(options.size() == result.getOptions().size());
    }

    private void initOptions() {
        options = listOfOptions();
    }


    private void initCar() {
        car = CarMother.init().build();
        carRepository.save(car);
    }

    private void initCompanyCar() {
        companyCar = CompanyCarMother.init().build();
        companyCar.setCar(null);
        companyCar.setOptions(new LinkedList<>());
    }

    private List<Option> listOfOptions() {

        List<Option> result = new ArrayList<>();
        IntStream.range(0, 5).forEach(index -> {
            Option option = OptionMother.init().build();
            option.setDescription("option" + index);
            option.setCar(car);
            optionRepository.save(option);
            result.add(option);
        });
        return result;
    }
}
