package com.realdolmen.fleet.service.impl;

import com.realdolmen.fleet.Application;
import com.realdolmen.fleet.SpringBootTransactionalIntegrationTest;
import com.realdolmen.fleet.TestConfig;
import com.realdolmen.fleet.domain.*;
import com.realdolmen.fleet.mother.*;
import com.realdolmen.fleet.repository.*;
import com.realdolmen.fleet.vo.OrderViewObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, Application.class})
@TestPropertySource(locations = "/test.properties")
@ActiveProfiles("TST")
@SpringBootTransactionalIntegrationTest

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

    @Autowired
    private FunctionalLevelRepository functionalLevelRepository;

    private OrderViewObject orderViewObject;

    private CompanyCar companyCar;
    private Car car;
    private ArrayList<Option> options;
    private UserCarHistory userCarHistory;
    private FunctionalLevel level;

    @Mock
    private AuthServiceImpl authService;

    private User user;

    @Before
    public void init() {
        orderViewObject = new OrderViewObject();

        initLevel();
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

    private void initLevel() {
        this.level = FunctionalLevelMother.init().build();
        level.setFLevel(6);
        functionalLevelRepository.save(level);
    }

    private void initUser() {
        this.user = UserMother.init().build();
        user.setUsername("flowUser");
        user.setFunctionalLevel(level);
        userRepository.save(user);
    }

    private void initUserCarHistory() {
        this.userCarHistory = UserCarHistoryMother.init().build();
        userCarHistory.setCompanyCar(null);
        userCarHistory.setUser(null);
    }

    private void initCompanyCar() {
        this.companyCar = CompanyCarMother.init().build();
        companyCar.setOptions(new LinkedList<>());
        companyCar.setCar(null);
    }

    private void initOptions() {
        this.options = new ArrayList<>();
        IntStream.range(0, 3).forEach(index -> {
            Option option = OptionMother.init().build();
            option.setCar(car);
            option.setDescription("option" + index);
            optionRepository.save(option);
            this.options.add(option);
        });
    }

    private void initCar() {
        this.car = CarMother.init().build();
        car.setCategory(level);
        carRepository.save(car);
    }

    @Test
//    @DirtiesContext
    public void saveSavesACompanyCar() {
        assertNull(companyCarRepository.findOne(1L));
        orderFlowService.save(orderViewObject);
        List<CompanyCar> all = (List<CompanyCar>) companyCarRepository.findAll();
        CompanyCar result = companyCarRepository.findOne(1L);


//        assertNotNull(result.getId());
        assertEquals(all.size(), 1);
        assertCompanyCar(all.get(0), orderViewObject.getCompanyCar());
    }

    private void assertCompanyCar(CompanyCar result, CompanyCar companyCar) {
        assertEquals(companyCar.getConcession(), result.getConcession());
        assertEquals(companyCar.getActive(), result.getActive());
        assertEquals(companyCar.getApproved(), result.getApproved());
    }

    @Test
//    @DirtiesContext
    public void saveSavesCompanyCarAndCarRelation() {
        assertNull(companyCarRepository.findOne(1L));

        orderFlowService.save(orderViewObject);

        Car resultCar = carRepository.findOne(1L);
        List<Car> allCars = carRepository.findAll();
        CompanyCar resultCompanyCar = companyCarRepository.findOne(1L);
        List<CompanyCar> allCompanyCars = (List<CompanyCar>) companyCarRepository.findAll();

        assertEquals(allCompanyCars.get(0).getCar().getId(), allCars.get(0).getId());
    }

    @Test
//    @DirtiesContext
    public void saveSavesUserCarHistoryWithCompanyCarRelation() {
        orderFlowService.save(orderViewObject);

        UserCarHistory userCarHistory = userCarHistoryRepository.findOne(1L);
        List<UserCarHistory> all1 = (List<UserCarHistory>) userCarHistoryRepository.findAll();
        List<CompanyCar> all = (List<CompanyCar>) companyCarRepository.findAll();
        CompanyCar companyCar = companyCarRepository.findOne(1L);


//        assertEquals(userCarHistory.getCompanyCar().getId(), companyCar.getId());
        assertEquals(all1.get(0).getCompanyCar().getId(), all.get(0).getId());
        assertEquals(all.size(), 1);
    }


    @Test
//    @DirtiesContext
    public void saveSavesUserCarHistoryWithUserRelation() {
        orderFlowService.save(orderViewObject);
        UserCarHistory userCarHistory = userCarHistoryRepository.findOne(1L);
        assertEquals(userCarHistory.getUser().getId(), user.getId());
    }

}
