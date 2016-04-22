package com.realdolmen.fleet.repository;

import com.realdolmen.fleet.SpringBootTransactionalIntegrationTest;
import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.Option;
import com.realdolmen.fleet.mother.CarMother;
import com.realdolmen.fleet.mother.OptionMother;
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
public class OptionRepositoryIntegrationTest {

    private Option correctOne, correctTwo, correctThree;
    private Option falseOne, falseTwo;

    List<Option> correctOptions;
    List<Option> falseOptions;

    private Car falseCar, correctCar;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private CarRepository carRepository;

    @Before
    public void init() {
        initOptions();
        this.correctOptions = Arrays.asList(correctOne, correctTwo, correctThree);
        this.falseOptions = Arrays.asList(falseOne, falseTwo);


        falseCar = CarMother.init().build();
        carRepository.save(falseCar);

        correctCar = CarMother.init().build();
        carRepository.save(correctCar);

        correctOptions.forEach(option -> {
            option.setCar(correctCar);
            optionRepository.save(option);
        });

        falseOptions.forEach(option -> {
            option.setCar(falseCar);
            optionRepository.save(option);
        });
    }

    private void initOptions() {
        this.correctOne = OptionMother.init().build();
        this.correctTwo = OptionMother.init().build();
        this.correctThree = OptionMother.init().build();

        this.falseOne = OptionMother.init().build();
        this.falseTwo = OptionMother.init().build();
    }

    @Test
    public void findByCarAndExpectOnlyTheOnesWithCorrectCar() {
        List<Option> resultOptions = optionRepository.findByCar(correctCar);
        assertTrue(resultOptions.size() == 3);

    }
}
