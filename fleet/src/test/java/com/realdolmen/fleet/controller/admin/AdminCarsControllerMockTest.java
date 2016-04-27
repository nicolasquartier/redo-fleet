package com.realdolmen.fleet.controller.admin;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.repository.CarRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
    public void createCarPageIsFound() throws Exception {


        mvc.perform(get("/admin/cars/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/createcar"))
                .andExpect(model().attributeExists("car"))
        ;
    }

    @Test
    public void editCarPageIsFound() throws Exception {
        Long id = 1L;
        when(carRepository.findOne(id)).thenReturn(carOne);
        preformGetForAndExpectCarModelView("/admin/cars/{id}", id, status().isOk());
    }

    @Test
    public void shouldProcessUpdateCar() throws Exception {
        CarRepository mockRepo = mock(CarRepository.class);
        AdminCarsController carsController = new AdminCarsController(mockRepo);
        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(carsController)
                .build();
        Long id = 1L;
        carOne.setId(id);
        mockMvc.perform(post("/admin/cars/" + carOne.getId()))
                .andExpect(redirectedUrl("/admin/cars"));
    }

    private List<Car> listOf4Cars() {
        return Arrays.asList(carOne, carTwo, carThree, carFour);
    }

    private void preformGetForAndExpectCarModelView(String path, Long id, ResultMatcher normalUser) throws Exception {
        mvc.perform(get(path, id))
                .andExpect(normalUser)
                .andExpect(model().attributeExists("car"))
                .andExpect(model().attribute("car", hasProperty("id")));
    }
}
