package com.realdolmen.fleet.controller.admin;

import com.realdolmen.fleet.TestConfig;
import com.realdolmen.fleet.controller.admin.AdminCarsController;
import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.repositories.CarRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration(value = "resources/templates/")
public class AdminCarsControllerMockTest {

    private MockMvc mvc;

    @Mock
    private CarRepository carRepository;

    @Mock
    private Car carOne, carTwo, carThree, carFour;

    @InjectMocks
    private AdminCarsController editCarsController;

    @Before
    public void init() {
        mvc = MockMvcBuilders
                .standaloneSetup(editCarsController)
                .build();

        when(carRepository.findAll()).thenReturn(listOf4Cars());
    }

    @Test
    public void carsAreAvailableOnView() throws Exception {

        listOf4Cars();

        mvc.perform(get("/admin/cars"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/allcars"))
                .andExpect(model().attributeExists("cars"))
                .andExpect(model().attribute("cars", hasSize(4)))
        ;
    }

    private List<Car> listOf4Cars() {
        return Arrays.asList(carOne, carTwo, carThree, carFour);
    }
}