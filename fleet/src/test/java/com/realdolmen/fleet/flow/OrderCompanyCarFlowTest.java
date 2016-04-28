package com.realdolmen.fleet.flow;


import com.realdolmen.fleet.domain.*;
import com.realdolmen.fleet.repository.CarRepository;
import com.realdolmen.fleet.repository.FunctionalLevelRepository;
import com.realdolmen.fleet.repository.OptionRepository;
import com.realdolmen.fleet.repository.UserCarHistoryRepository;
import com.realdolmen.fleet.service.impl.AuthServiceImpl;
import com.realdolmen.fleet.service.impl.CompanyCarServiceImpl;
import com.realdolmen.fleet.service.impl.OrderFlowServiceImpl;
import com.realdolmen.fleet.vo.OrderViewObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.MockRequestContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderCompanyCarFlowTest extends AbstractXmlFlowExecutionTests {

    @Mock
    private AuthServiceImpl authService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private UserCarHistoryRepository userCarHistoryRepository;

    @Mock
    private OptionRepository optionRepository;

    @Mock
    private CompanyCarServiceImpl companyCarController;

    @Mock
    private FunctionalLevelRepository functionalLevelRepository;

    @Mock
    private OrderFlowServiceImpl orderFlowService;

    @Mock
    private Car car;

    @Mock
    private Option optionOne, optionTwo;

    @Mock
    private CompanyCar companyCar;

    @Mock
    private OrderViewObject orderViewObject;

    @Mock
    private User user;

    @Mock
    private UserCarHistory userCarHistory;

    private MockRequestContext requestContext;

    @Override
    protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory flowDefinitionResourceFactory) {
        return flowDefinitionResourceFactory.createFileResource("src/main/resources/templates/orderCompanyCar/orderCompanyCar-flow.xml");
    }

    @Override
    protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
        builderContext.registerBean("carRepository", carRepository);
        builderContext.registerBean("optionRepository", optionRepository);
        builderContext.registerBean("companyCarController", companyCarController);
        builderContext.registerBean("orderFlowService", orderFlowService);
        builderContext.registerBean("authServiceImpl", authService);
        builderContext.registerBean("userCarHistoryRepository", userCarHistoryRepository);
    }

    @Before
    public void init() {
        when(authService.getCurrentUser()).thenReturn(user);
        when(userCarHistoryRepository.findAllByUserAndCompanyCarApprovedFalse(user)).thenReturn(Collections.singletonList(userCarHistory));

        when(carRepository.findOne(any(Long.class))).thenReturn(car);
        when(optionRepository.findByCar(any(Car.class))).thenReturn(Arrays.asList(optionOne, optionTwo));

        requestContext = new MockRequestContext();
    }

    @Test
    public void testStartOrderCompanyCarFlow() {
        MockExternalContext context = initAndStartFlow();

        assertCurrentStateEquals("configureCar");
        assertTrue(getRequiredConversationAttribute("car") instanceof Car);

        Car carOnView = (Car) getConversationScope().get("car");
        assertSame(carOnView, car);
    }

    private MockExternalContext initAndStartFlow() {
        requestContext.getConversationScope().put("car", car);
        MutableAttributeMap input = new LocalAttributeMap<>();
        input.put("carId", "1");
        MockExternalContext context = new MockExternalContext();
        context.setCurrentUser("user");
        startFlow(input, context);
        return context;
    }

    @Test
    public void testConfigureCarToConfirmCarTransistion() {
        setCurrentState("configureCar");
        MockExternalContext context = new MockExternalContext();
        context.setCurrentUser("user");
        context.setEventId("confirm");
        resumeFlow(context);

        assertCurrentStateEquals("confirm");
    }

    @Test
    public void testConfirmCarToFleetMetaDataTransistion() {
        MockExternalContext context = initAndStartFlow();
        setCurrentState("confirm");
        requestContext.getConversationScope().put("orderViewObject", orderViewObject);

        context.setEventId("goToFleetMetaData");
        resumeFlow(context);

        assertCurrentStateEquals("fleetMetaData");

    }

    @Test
    public void testCarObjectIsOnConversationScopeInConfirmPage() {
        MockExternalContext context = initAndStartFlow();
        context.setEventId("confirm");
        resumeFlow(context);

        assertCurrentStateEquals("confirm");
        Car carOnView = (Car) getConversationScope().get("car");
        assertSame(carOnView, car);
    }

    //@TODO @FIXME: testOrderCompanyCar

    @Test
    public void testCancelBtnOnConfigureCarOptionsPage() {
        setCurrentState("configureCar");
        MockExternalContext context = new MockExternalContext();
        context.setEventId("cancel");
        resumeFlow(context);

        assertFlowExecutionEnded();
        assertFlowExecutionOutcomeEquals("endState");
    }

    @Test
    public void testCancelBtnOnConfirmCompanyCarPage() {
        setCurrentState("confirm");
        MockExternalContext context = new MockExternalContext();
        context.setEventId("cancel");
        resumeFlow(context);

        assertFlowExecutionEnded();
        assertFlowExecutionOutcomeEquals("endState");
    }

    @Test
    public void testCancelBtnOnFleetMetaDataPage() {
        setCurrentState("fleetMetaData");
        MockExternalContext context = new MockExternalContext();
        context.setEventId("cancel");
        resumeFlow(context);

        assertFlowExecutionEnded();
        assertFlowExecutionOutcomeEquals("endState");
    }

    @Test
    public void testBackFromFleetMetaDataToConfirmCompanyCar() {
        setCurrentState("fleetMetaData");
        MockExternalContext context = new MockExternalContext();
        context.setEventId("back");
        resumeFlow(context);

        assertCurrentStateEquals("confirm");
    }

    @Test
    public void testBackFromConfirmCompanyCarToConfigureOptions() {
        setCurrentState("confirm");
        MockExternalContext context = new MockExternalContext();
        context.setEventId("back");
        resumeFlow(context);

        assertCurrentStateEquals("configureCar");
    }

}
