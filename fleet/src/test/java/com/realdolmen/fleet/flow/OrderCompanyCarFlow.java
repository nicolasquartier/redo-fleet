package com.realdolmen.fleet.flow;


import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.CompanyCar;
import com.realdolmen.fleet.domain.FunctionalLevel;
import com.realdolmen.fleet.domain.Option;
import com.realdolmen.fleet.mother.CarMother;
import com.realdolmen.fleet.mother.FunctionalLevelMother;
import com.realdolmen.fleet.repositories.CarRepository;
import com.realdolmen.fleet.repositories.FunctionalLevelRepository;
import com.realdolmen.fleet.repositories.OptionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderCompanyCarFlow extends AbstractXmlFlowExecutionTests {

    @Mock
    private CarRepository carRepository;

    @Mock
    private OptionRepository optionRepository;

    @Mock
    private FunctionalLevelRepository functionalLevelRepository;

    @Mock
    private Car car;

    @Mock
    private Option optionOne, optionTwo;

    private  MockRequestContext requestContext;

    @Override
    protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory flowDefinitionResourceFactory) {
        return flowDefinitionResourceFactory.createFileResource("src/main/resources/templates/orderCompanyCar/orderCompanyCar-flow.xml");
    }

    @Override
    protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
        builderContext.registerBean("carRepository", carRepository);
        builderContext.registerBean("optionRepository", optionRepository);
    }

    @Before
    public void init() {
        when(carRepository.findOne(any(Long.class))).thenReturn(car);
        when(optionRepository.findByCar(any(Car.class))).thenReturn(Arrays.asList(optionOne, optionTwo));

        requestContext = new MockRequestContext();

        System.out.println("start test");
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
}
