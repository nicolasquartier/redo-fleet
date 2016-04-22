package com.realdolmen.fleet.service.impl;

import com.realdolmen.fleet.Application;
import com.realdolmen.fleet.SpringBootTransactionalIntegrationTest;
import com.realdolmen.fleet.TestConfig;
import com.realdolmen.fleet.domain.*;
import com.realdolmen.fleet.mother.*;
import com.realdolmen.fleet.repository.*;
import com.realdolmen.fleet.vo.OrderViewObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, Application.class})
@TestPropertySource(locations = "/test.properties")
@ActiveProfiles("TST")
public class OrderFlowServiceIntegrationTest {

    @Autowired
    private OrderFlowServiceImpl orderFlowService;

    @Autowired
    private CompanyCarRepository companyCarRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCarHistoryRepository userCarHistoryRepository;

    private OrderViewObject orderViewObject;

    private CompanyCar companyCar;
    private Car car;
    private ArrayList<Option> options;
    private UserCarHistory userCarHistory;

    private AuthServiceImpl authService;

    private User user;

    @Before
    public void init() {
        orderViewObject = new OrderViewObject();

        initCar();
        initOptions();
        initCompanyCar();
        initUserCarHistory();
        initUser();

        orderViewObject.setCar(car);
        orderViewObject.setUserCarHistory(userCarHistory);
        orderViewObject.setCompanyCar(companyCar);
        orderViewObject.setOptions(options);

        authService = mock(AuthServiceImpl.class);
        when(authService.getCurrentUser()).thenReturn(user);
        orderFlowService.setAuthenticationService(authService);
    }

    private void initUser() {
        this.user = UserMother.init().build();
        user.setUsername("flowUser");
        userRepository.save(user);
    }

    private void initUserCarHistory() {
        this.userCarHistory = UserCarHistoryMother.init().build();
        userCarHistory.setCompanyCar(null);
        userCarHistory.setUser(null);
    }

    private void initCompanyCar() {
        this.companyCar = CompanyCarMother.init().build();
        companyCar.setOptions(new Vector<>());
        companyCar.setCar(null);
    }

    private void initOptions() {
        this.options = new ArrayList<>();
        IntStream.range(0,3).forEach(index -> {
            Option option = OptionMother.init().build();
            option.setCar(car);
            option.setDescription("option" + index);
            optionRepository.save(option);
            this.options.add(option);
        });
    }

    private void initCar() {
        this.car = CarMother.init().build();
        carRepository.save(car);
    }

    @Test
    public void saveSavesACompanyCar() {
//        assertNull(companyCarRepository.findOne(1L));
        orderFlowService.save(orderViewObject);
        CompanyCar result = companyCarRepository.findOne(1L);

        assertNotNull(result.getId());
        assertCompanyCar(result, orderViewObject.getCompanyCar());
    }

    private void assertCompanyCar(CompanyCar result, CompanyCar companyCar) {
        assertEquals(companyCar.getConcession(), result.getConcession());
        assertEquals(companyCar.getActive(), result.getActive());
        assertEquals(companyCar.getApproved(), result.getApproved());
    }

    @Test
    public void saveSavesCompanyCarAndCarRelation() {
        assertNull(companyCarRepository.findOne(1L));

        orderFlowService.save(orderViewObject);

        Car resultCar = carRepository.findOne(1L);
        CompanyCar resultCompanyCar = companyCarRepository.findOne(1L);

        assertEquals(resultCompanyCar.getCar().getId(), resultCar.getId());
    }

    @Test
    public void saveSavesUserCarHistoryWithCompanyCarRelation() {
        orderFlowService.save(orderViewObject);

        UserCarHistory userCarHistory = userCarHistoryRepository.findOne(1L);
        CompanyCar companyCar = companyCarRepository.findOne(1L);

        assertEquals(userCarHistory.getCompanyCar().getId(), companyCar.getId());
    }

    // test link cc and options

    //  test link user and uch
}
