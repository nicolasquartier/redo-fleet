package com.realdolmen.fleet.domain;

import com.realdolmen.fleet.domain.enums.OptionType;
import com.realdolmen.fleet.mother.OptionMother;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OptionTest {

    private Option option;

    @Mock
    private CompanyCar companyCarOne, companyCarTwo, companyCarThree;

    @Mock
    private Car car;

    @Before
    public void init() {
        this.option = OptionMother.init().build();
    }


    @Test
    public void id() throws Exception {
        Long expected = 666L;
        option.setId(expected);
        assertNotNull(option.getId());
        assertEquals(expected, option.getId());
    }

    @Test
    public void version() throws Exception {
        Long expected = 666L;
        option.setVersion(expected);
        assertEquals(expected, option.getVersion());
    }

    @Test
    public void car() {
        option.setCar(car);
        assertSame(car, option.getCar());
    }


    @Test
    public void addOption() {
        option.addCompanyCar(companyCarOne);
        assertTrue(option.getCompanyCarList().size() == 1);
        assertSame(companyCarOne, option.getCompanyCarList().get(0));
    }

    @Test
    public void removeCompanyCar() {
        option.setCompanyCarList(new LinkedList<>(Arrays.asList(companyCarOne, companyCarTwo, companyCarThree)));
        assertTrue(option.getCompanyCarList().size() == 3);
        assertTrue(option.getCompanyCarList().contains(companyCarOne));
        assertTrue(option.getCompanyCarList().contains(companyCarTwo));
        assertTrue(option.getCompanyCarList().contains(companyCarThree));

        option.removeCompanyCar(companyCarTwo);

        assertTrue(option.getCompanyCarList().size() == 2);
        assertTrue(option.getCompanyCarList().contains(companyCarOne));
        assertFalse(option.getCompanyCarList().contains(companyCarTwo));
        assertTrue(option.getCompanyCarList().contains(companyCarThree));
    }

    @Test
    public void description() {
        String expected = "expected";
        option.setDescription(expected);
        assertSame(expected, option.getDescription());
    }


    @Test
    public void type() {
        OptionType expected = OptionType.DEALER;
        option.setType(expected);
        assertSame(expected, option.getType());
    }
}