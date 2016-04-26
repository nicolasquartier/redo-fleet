package com.realdolmen.fleet.domain;

import com.realdolmen.fleet.mother.CarMother;
import com.realdolmen.fleet.mother.FunctionalLevelMother;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.realdolmen.fleet.domain.enums.Brand.VOLKSWAGEN;
import static com.realdolmen.fleet.domain.enums.CarType.BREAK;
import static com.realdolmen.fleet.domain.enums.FuelType.DIESEL;
import static com.realdolmen.fleet.domain.enums.RimType.ALUMINIUM;
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
        assertEquals(options, car.getOptions());
    }

    @Test
    public void addOption() {
        car.addOption(optionOne);
        assertTrue(car.getOptions().size() == 1);
        assertSame(optionOne, car.getOptions().get(0));
    }

    @Test
    public void removeOption() {
        car.setOptions(new LinkedList<>(Arrays.asList(optionOne, optionTwo, optionThree)));
        assertTrue(car.getOptions().size() == 3);
        assertTrue(car.getOptions().contains(optionOne));
        assertTrue(car.getOptions().contains(optionTwo));
        assertTrue(car.getOptions().contains(optionThree));

        car.removeOption(optionTwo);

        assertTrue(car.getOptions().size() == 2);
        assertTrue(car.getOptions().contains(optionOne));
        assertFalse(car.getOptions().contains(optionTwo));
        assertTrue(car.getOptions().contains(optionThree));
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
        LocalDate expected = LocalDate.of(10,10,10);
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
        assertEquals(expected, car.getHybrid());
    }


    @Test
    public void active() throws Exception {
        Boolean expected = false;
        car.setActive(expected);
        assertEquals(expected, car.getActive());
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

    @Test
    public void rimType() {
        car.setRimType(ALUMINIUM);
        assertEquals(ALUMINIUM, car.getRimType());
    }

    @Test
    public void idealKm() throws Exception {
        Integer expected = 666;
        car.setIdealKm(expected);
        assertEquals(expected, car.getIdealKm());
    }

    @Test
    public void maxKm() throws Exception {
        Integer expected = 666;
        car.setMaxKm(expected);
        assertEquals(expected, car.getMaxKm());
    }

    @Test
    public void listPrice() throws Exception {
        Double expected = 666.66;
        car.setListPrice(expected);
        assertEquals(expected, car.getListPrice());
    }

    @Test
    public void monthlyBenefit() throws Exception {
        Double expected = 666.66;
        car.setMonthlyBenefit(expected);
        assertEquals(expected, car.getMonthlyBenefit());
    }

    @Test
    public void upgradeAmount() throws Exception {
        Double expected = 666.66;
        car.setUpgradeAmount(expected);
        assertEquals(expected, car.getUpgradeAmount());
    }

    @Test
    public void downgradeAmount() throws Exception {
        Double expected = 666.66;
        car.setDowngradeAmount(expected);
        assertEquals(expected, car.getDowngradeAmount());
    }

    @Test
    public void hPower() throws Exception {
        Integer expected = 666;
        car.setHPower(expected);
        assertEquals(expected, car.getHPower());
    }

    @Test
    public void engine() throws Exception {
        String expected = "beast";
        car.setEngine(expected);
        assertEquals(expected, car.getEngine());
    }

    @Test
    public void getLevel() {
        Integer expected = 6;

        FunctionalLevel levelMock = Mockito.mock(FunctionalLevel.class);
        Mockito.when(levelMock.getFLevel()).thenReturn(expected);
        car.setCategory(levelMock);

        assertSame(car.getLevel(), expected);
    }
}