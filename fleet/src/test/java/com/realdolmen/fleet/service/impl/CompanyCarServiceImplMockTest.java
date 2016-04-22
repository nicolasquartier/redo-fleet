package com.realdolmen.fleet.service.impl;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.CompanyCar;
import com.realdolmen.fleet.domain.Option;
import com.realdolmen.fleet.repository.CompanyCarRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CompanyCarServiceImplMockTest {

    @Mock
    private CompanyCarRepository companyCarRepository;

    @Mock
    private Car car;

    @Mock
    private Option optionOne, optionTwo, optionThree;

    @Mock
    private List<Option> options;

    @InjectMocks
    private CompanyCarServiceImpl companyCarService;

    @Before
    public void setUp() throws Exception {
        options = Arrays.asList(optionOne, optionTwo, optionThree);
    }

    @Test
    public void createCompanyCarWithOptionsSavesCompanyCar() {

        CompanyCar result = companyCarService.createCompanyCarWithOptions(car, options);

        assertEquals(car, result.getCar());

        assertTrue(result.getOptions().size() == 3);
        assertEquals(options, result.getOptions());
    }
}
