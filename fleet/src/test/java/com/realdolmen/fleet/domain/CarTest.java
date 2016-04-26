package com.realdolmen.fleet.domain;

import com.realdolmen.fleet.domain.enums.Brand;
import com.realdolmen.fleet.domain.enums.CarType;
import com.realdolmen.fleet.domain.enums.FuelType;
import com.realdolmen.fleet.mother.CarMother;
import com.realdolmen.fleet.mother.FunctionalLevelMother;
import com.realdolmen.fleet.mother.OptionMother;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.*;

import static com.realdolmen.fleet.domain.enums.Brand.VOLKSWAGEN;
import static com.realdolmen.fleet.domain.enums.CarType.*;
import static com.realdolmen.fleet.domain.enums.FuelType.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CarTest {

    private Car car;

    @Mock
    private Option optionOne, optionTwo, optionThree;

    @Before
    public void setUp() throws Exception {
        this.car = CarMother.init().build();
    }

    @Test
    public void id() throws Exception {
        Long expected = 666L;
        car.setId(expected);
        assertNotNull(car.getId());
        assertEquals(expected, car.getId());
    }

    @Test
    public void version() throws Exception {
        Long expected = 666L;
        car.setVersion(expected);
        assertEquals(expected, car.getVersion());
    }

    @Test
    public void options() throws Exception {
        List<Option> options = Arrays.asList(optionOne, optionTwo, optionThree);
        car.setOptions(options);
        assertTrue(3 == car.getOptions().size());
        assertSame(options, car.getOptions());
    }

    @Test
    public void brand() throws Exception {
        car.setBrand(VOLKSWAGEN);
        assertEquals(VOLKSWAGEN, car.getBrand());
    }

    @Test
    public void fuelType() throws Exception {
        car.setFuelType(DIESEL);
        assertEquals(DIESEL, car.getFuelType());
    }

    @Test
    public void model() throws Exception {
        String expected = "beetle";
        car.setModel(expected);
        assertEquals(expected, car.getModel());
    }

    @Test
    public void productionDate() {
        Date expected = new Date();
        car.setProductionDate(expected);
        assertEquals(expected, car.getProductionDate());
    }

    @Test
    public void fiscalHorsePower() {
        Integer expected = 666;
        car.setFiscalHorsePower(expected);
        assertEquals(car.getFiscalHorsePower(), expected);
    }

    @Test
    public void pack() throws Exception {
        String expected = "pack";
        car.setPack(expected);
        assertEquals(expected, car.getPack());
    }

    @Test
    public void type() throws Exception {
        car.setType(BREAK);
        assertEquals(BREAK, car.getType());
    }

    @Test
    public void hybrid() throws Exception {
        Boolean expected = false;
        car.setHybrid(expected);
        assertEquals(expected, car.isHybrid());
    }


    @Test
    public void active() throws Exception {
        Boolean expected = false;
        car.setActive(expected);
        assertEquals(expected, car.isActive());
    }

    @Test
    public void emission() throws Exception {
        Integer expected = 666;
        car.setEmission(expected);
        assertEquals(expected, car.getEmission());
    }

    @Test
    public void category() throws Exception {
        FunctionalLevel expected = FunctionalLevelMother.init().build();
        car.setCategory(expected);
        assertSame(expected, car.getCategory());
    }


    @Test
    public void thumbnail() throws Exception {
        String expected = "prettyimage";
        car.setThumbnail(expected);
        assertEquals(expected, car.getThumbnail());
    }


}