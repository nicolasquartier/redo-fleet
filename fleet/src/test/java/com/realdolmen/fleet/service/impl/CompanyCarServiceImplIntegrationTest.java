package com.realdolmen.fleet.service.impl;

import com.realdolmen.fleet.SpringBootTransactionalIntegrationTest;
import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.CompanyCar;
import com.realdolmen.fleet.domain.Option;
import com.realdolmen.fleet.mother.CarMother;
import com.realdolmen.fleet.mother.OptionMother;
import com.realdolmen.fleet.repository.CarRepository;
import com.realdolmen.fleet.repository.CompanyCarRepository;
import com.realdolmen.fleet.repository.OptionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

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

    @Before
    public void init() {
        initCar();
        initOptions();
    }

    @Test
    public void createCompanyCarWithOptionsCreatesACarWithOptions() {
        CompanyCar result = companyCarService.createCompanyCarWithOptions(car, options);

        assertNotNull(result.getId());
        CompanyCar dbResult = companyCarRepository.findOne(result.getId());
        assertSame(result, dbResult);

        assertSame(car, dbResult.getCar());
        assertSame(result.getCar(), dbResult.getCar());

        assertTrue(options.size() == dbResult.getOptions().size());
    }


    private void initOptions() {
        options = listOfOptions();
    }

    private void initCar() {
        car = CarMother.init().build();
        carRepository.save(car);
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
