package com.realdolmen.fleet.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

public class LocalDateAttributeConverterTest {

    private LocalDateAttributeConverter converter;

    @Before
    public void setUp() {
        converter = new LocalDateAttributeConverter();
    }

    @Test
    public void convertToEntityAttributeTest() {
        LocalDate localDate = converter.convertToEntityAttribute(new Date(110, 10, 10));
        Assert.assertEquals(localDate.getYear(), 2010);
        Assert.assertEquals(localDate.getMonthValue(), 11);
        Assert.assertEquals(localDate.getDayOfMonth(), 10);
    }
}