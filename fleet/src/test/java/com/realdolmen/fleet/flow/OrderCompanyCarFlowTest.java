package com.realdolmen.fleet.flow;


import com.realdolmen.fleet.controller.CompanyCarController;
import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.CompanyCar;
import com.realdolmen.fleet.domain.FunctionalLevel;
import com.realdolmen.fleet.domain.Option;
import com.realdolmen.fleet.domain.enums.Brand;
import com.realdolmen.fleet.domain.enums.FuelType;
import com.realdolmen.fleet.mother.CarMother;
import com.realdolmen.fleet.mother.FunctionalLevelMother;
import com.realdolmen.fleet.repositories.CarRepository;
import com.realdolmen.fleet.repositories.FunctionalLevelRepository;
import com.realdolmen.fleet.repositories.OptionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.MockRequestContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

import javax.persistence.EntityManager;
import javax.validation.OverridesAttribute;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderCompanyCarFlow extends AbstractXmlFlowExecutionTests {

    @Mock
    private CarRepository carRepository;

    @Mock
    private OptionRepository optionRepository;

    @Mock
    private CompanyCarController companyCarController;

    @Mock
    private FunctionalLevelRepository functionalLevelRepository;

    @Mock
    private Car car;

    @Mock
    private Option optionOne, optionTwo;

    @Mock
    private CompanyCar companyCar;

    private  MockRequestContext requestContext;

    @Override
    protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory flowDefinitionResourceFactory) {
        return flowDefinitionResourceFactory.createFileResource("src/main/resources/templates/orderCompanyCar/orderCompanyCar-flow.xml");
    }

    @Override
    protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
        builderContext.registerBean("carRepository", carRepository);
        builderContext.registerBean("optionRepository", optionRepository);
        builderContext.registerBean("companyCarController", companyCarController);
    }

    @Before
    public void init() {
        when(carRepository.findOne(any(Long.class))).thenReturn(car);
        when(optionRepository.findByCar(any(Car.class))).thenReturn(Arrays.asList(optionOne, optionTwo));
        when(companyCarController.createCompanyCar(any(Car.class), Matchers.anyListOf(Option.class))).thenReturn(companyCar);

        requestContext = new MockRequestContext();
    }

    @Test
    public void testStartOrderCompanyCarFlow() {
        requestContext.getConversationScope().put("car", car);

        MutableAttributeMap input = new LocalAttributeMap<>();
        input.put("carId", "1");
        MockExternalContext context = new MockExternalContext();
        context.setCurrentUser("user");
        startFlow(input, context);

        assertCurrentStateEquals("configureCar");
        assertTrue(getRequiredConversationAttribute("car") instanceof Car);
    }

    @Test
    public void testConfirmCompanyCarFlow() {
        setCurrentState("configureCar");

        MockExternalContext context = new MockExternalContext();
        context.setCurrentUser("user");
        context.setEventId("confirm");
        resumeFlow(context);

        assertCurrentStateEquals("confirm");
    }

    public Car createTestCar() {
        Car car = new Car();
        FunctionalLevel category = new FunctionalLevel();
        category.setFLevel(1);
        car.setCategory(category);
        car.setEmission(99);
        car.setBrand(Brand.AUDI);
        car.setProductionDate(new Date());
        car.setActive(true);
        car.setHybrid(false);
        car.setId(1L);
        car.setModel("ne witn");
        car.setPack("zukèèn lik van de acaddemict");
        car.setFiscalHorsePower(99);
        car.setFuelType(FuelType.DIESEL);
        Option option = new Option();
        option.setDescription("description 1");
        car.setOptions(Arrays.asList(option));
        return car;
    }
}
