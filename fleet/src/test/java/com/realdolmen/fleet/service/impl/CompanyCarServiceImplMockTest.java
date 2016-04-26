package com.realdolmen.fleet.service.impl;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.CompanyCar;
import com.realdolmen.fleet.domain.Option;
import com.realdolmen.fleet.mother.CompanyCarMother;
import com.realdolmen.fleet.repository.CompanyCarRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

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

    @Mock
    private CompanyCar companyCar;

    @Before
    public void setUp() throws Exception {
        options = Arrays.asList(optionOne, optionTwo, optionThree);
    }

    @Test
    public void createCompanyCarWithOptionsSavesCompanyCar() {
        companyCarService.createCompanyCarWithOptions(companyCar, car, options);
        verify(companyCarRepository, times(3)).save(companyCar);
        verifyNoMoreInteractions(companyCarRepository);
    }


    @Test
    public void createCompanyCarWithOptionsSetsCar() {
        companyCarService.createCompanyCarWithOptions(companyCar, car, options);
        verify(companyCar, times(1)).setCar(car);
    }


    @Test
    public void createCompanyCarWithOptionsSetsOptionsManyToManyRelation() {
        companyCarService.createCompanyCarWithOptions(companyCar, car, options);

        verify(companyCar, times(1)).addOption(optionOne);
        verify(companyCar, times(1)).addOption(optionTwo);
        verify(companyCar, times(1)).addOption(optionThree);

        verify(optionOne, times(1)).addCompanyCar(companyCar);
        verify(optionTwo, times(1)).addCompanyCar(companyCar);
        verify(optionThree, times(1)).addCompanyCar(companyCar);

        verifyNoMoreInteractions(optionOne, optionTwo, optionThree);
    }
}
