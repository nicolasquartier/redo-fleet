package com.realdolmen.fleet.domain;

import com.realdolmen.fleet.mother.CompanyCarMother;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CompanyCarTest {

    private CompanyCar companyCar;

    @Mock
    private Car car;

    @Mock
    private Option optionOne, optionTwo, optionThree;

    @Before
    public void init() {
        this.companyCar = CompanyCarMother.init().build();
    }

    @Test
    public void id() throws Exception {
        Long expected = 666L;
        companyCar.setId(expected);
        assertNotNull(companyCar.getId());
        assertEquals(expected, companyCar.getId());
    }

    @Test
    public void version() throws Exception {
        Long expected = 666L;
        companyCar.setVersion(expected);
        assertEquals(expected, companyCar.getVersion());
    }

    @Test
    public void car() throws Exception {
        companyCar.setCar(car);
        assertEquals(car, companyCar.getCar());
    }

    @Test
    public void addOption() {
        companyCar.addOption(optionOne);
        assertTrue(companyCar.getOptions().size() == 1);
        assertSame(optionOne, companyCar.getOptions().get(0));
    }

    @Test
    public void removeOption() {
        companyCar.setOptions(new LinkedList<>(Arrays.asList(optionOne, optionTwo, optionThree)));
        assertTrue(companyCar.getOptions().size() == 3);
        assertTrue(companyCar.getOptions().contains(optionOne));
        assertTrue(companyCar.getOptions().contains(optionTwo));
        assertTrue(companyCar.getOptions().contains(optionThree));

        companyCar.removeOption(optionTwo);

        assertTrue(companyCar.getOptions().size() == 2);
        assertTrue(companyCar.getOptions().contains(optionOne));
        assertFalse(companyCar.getOptions().contains(optionTwo));
        assertTrue(companyCar.getOptions().contains(optionThree));
    }

    @Test
    public void active() throws Exception {
        Boolean expected = false;
        companyCar.setActive(expected);
        assertSame(expected, companyCar.getActive());
    }

    @Test
    public void approved() throws Exception {
        Boolean expected = false;
        companyCar.setApproved(expected);
        assertSame(expected, companyCar.getApproved());
    }

    @Test
    public void concession() throws Exception {
        String expected = "expected";
        companyCar.setConcession(expected);
        assertSame(expected, companyCar.getConcession());
    }

    @Test
    public void orderDate() throws Exception {
        LocalDate expected = LocalDate.now();
        companyCar.setOrderDate(expected);
        assertSame(expected, companyCar.getOrderDate());
    }


    @Test
    public void deliveryDate() throws Exception {
        Date expected = new Date();
        companyCar.setDeliveryDate(expected);
        assertSame(expected, companyCar.getDeliveryDate());
    }

    @Test
    public void getCategory() {
        Integer expected = 6;
        Mockito.when(car.getLevel()).thenReturn(expected);
        companyCar.setCar(car);

        assertEquals(companyCar.getCategory(), expected);
    }

}