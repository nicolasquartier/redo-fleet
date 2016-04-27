package com.realdolmen.fleet.service.impl;

import com.realdolmen.fleet.domain.*;
import com.realdolmen.fleet.repository.UserCarHistoryRepository;
import com.realdolmen.fleet.vo.OrderViewObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderFlowServiceImplMockTest {

    @Mock
    private UserCarHistory userCarHistory;

    @Mock
    private Car car;

    @Mock
    private CompanyCar companyCar;

    @Mock
    private List<Option> options;

    @Mock
    private CompanyCarServiceImpl companyCarService;

    @Mock
    private AuthServiceImpl authService;

    @Mock
    private UserCarHistoryRepository userCarHistoryRepository;

    @Mock
    private Option optionOne, optionTwo, optionThree;

    @Mock
    private User user;

    @InjectMocks
    private OrderFlowServiceImpl orderFlowService = new OrderFlowServiceImpl();

    @InjectMocks
    private OrderViewObject orderViewObject = new OrderViewObject();

    @Before
    public void init() {
        options = Arrays.asList(optionOne, optionTwo, optionThree);

        orderViewObject.setCar(car);
        orderViewObject.setOptions(options);
        orderViewObject.setCompanyCar(companyCar);
        orderViewObject.setUserCarHistory(userCarHistory);

        when(authService.getCurrentUser()).thenReturn(user);
    }

    @Test
    public void saveSavesACallsCompanyCarService() {
        orderFlowService.save(orderViewObject);
        verify(companyCarService, times(1)).createCompanyCarWithOptions(companyCar, car, options);
        verifyNoMoreInteractions(companyCarService);
    }

    @Test
    public void saveSavesASetsCompanyCarAndUserOnHistoryAndSavesBeforeAndAfter() {
        orderFlowService.save(orderViewObject);
        verify(userCarHistory, times(1)).setCompanyCar(companyCar);
        verify(userCarHistory, times(1)).setUser(user);
        verify(userCarHistoryRepository, times(1)).save(userCarHistory);
        verifyNoMoreInteractions(userCarHistoryRepository);
    }

}
