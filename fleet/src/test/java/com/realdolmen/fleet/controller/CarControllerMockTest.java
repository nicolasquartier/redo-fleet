package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.FunctionalLevel;
import com.realdolmen.fleet.domain.enums.Brand;
import com.realdolmen.fleet.domain.enums.CarType;
import com.realdolmen.fleet.repository.CarRepository;
import com.realdolmen.fleet.repository.FunctionalLevelRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration(value = "resources/templates/")
public class CarControllerMockTest {

    private MockMvc mvc;

    @Mock
    private CarRepository carRepository;

    @Mock
    private FunctionalLevelRepository functionalLevelRepository;

    @Mock
    private Car carOne, carTwo, carThree, carFour;

    @Mock
    private FunctionalLevel category;

    private CarType type;
    private Brand brand;

    @InjectMocks
    private CarController carController;

    @Before
    public void init() {
        type = CarType.BERLINE;
        brand = Brand.AUDI;
        mvc = MockMvcBuilders
                .standaloneSetup(carController)
                .build();

    }

    @Test
    public void carsAreAvailableOnView() throws Exception {

        listOf4Cars();
        when(carRepository.findByActive(true)).thenReturn(listOf4Cars());

        mvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(view().name("carcatalog"))
                .andExpect(model().attributeExists("cars"))
                .andExpect(model().attribute("cars", hasSize(4)))
        ;
    }

    @Test
    public void carsAreFilteredByCategoryAndTypeAndBrand() throws Exception {
        listOf4Cars();
        FunctionalLevel category = new FunctionalLevel();
        category.setFLevel(1);
        when(carRepository.findByCategoryAndTypeAndBrand(category, CarType.BERLINE, Brand.AUDI)).thenReturn(listOf4Cars());
        when(functionalLevelRepository.findByFLevel(anyInt())).thenReturn(category);
        mvc.perform(get("/cars/filter")
                .param("categoryLevel", "0")
                .param("type", "ALL")
                .param("brand", "ALL"))
                .andExpect(status().isOk())
                .andExpect(view().name("carcatalog"))
                .andExpect(model().attributeExists("noFilteredCarsFound"))
                .andExpect(model().attributeExists("wantedCategoryLevel"))
                .andExpect(model().attributeExists("wantedType"))
                .andExpect(model().attributeExists("wantedBrand"));

        mvc.perform(get("/cars/filter")
                .param("categoryLevel", "0")
                .param("type", "BERLINE")
                .param("brand", "AUDI"))
                .andExpect(status().isOk())
                .andExpect(view().name("carcatalog"))
                .andExpect(model().attributeExists("cars"))
                .andExpect(model().attribute("cars", hasSize(4)));

        mvc.perform(get("/cars/filter")
                .param("categoryLevel", "0")
                .param("type", "ALL")
                .param("brand", "AUDI"))
                .andExpect(status().isOk())
                .andExpect(view().name("carcatalog"))
                .andExpect(model().attributeExists("noFilteredCarsFound"))
                .andExpect(model().attributeExists("wantedCategoryLevel"))
                .andExpect(model().attributeExists("wantedType"))
                .andExpect(model().attributeExists("wantedBrand"));

        mvc.perform(get("/cars/filter")
                .param("categoryLevel", "0")
                .param("type", "BERLINE")
                .param("brand", "ALL"))
                .andExpect(status().isOk())
                .andExpect(view().name("carcatalog"))
                .andExpect(model().attributeExists("noFilteredCarsFound"))
                .andExpect(model().attributeExists("wantedCategoryLevel"))
                .andExpect(model().attributeExists("wantedType"))
                .andExpect(model().attributeExists("wantedBrand"));
    }

    @Test
    public void carsAreFilteredByTypeAndBrand() throws Exception {
        listOf4Cars();
        when(carRepository.findByTypeAndBrand(CarType.BERLINE, Brand.AUDI)).thenReturn(listOf4Cars());
        when(functionalLevelRepository.findByFLevel(anyInt())).thenReturn(category);
        mvc.perform(get("/cars/filter")
                .param("type", "BERLINE")
                .param("brand", "ALL"))
                .andExpect(status().isOk())
                .andExpect(view().name("carcatalog"))
                .andExpect(model().attributeExists("noFilteredCarsFound"))
                .andExpect(model().attributeExists("wantedCategoryLevel"))
                .andExpect(model().attributeExists("wantedType"))
                .andExpect(model().attributeExists("wantedBrand"));

        mvc.perform(get("/cars/filter")
                .param("type", "ALL")
                .param("brand", "ALL"))
                .andExpect(status().isOk())
                .andExpect(view().name("carcatalog"))
                .andExpect(model().attributeExists("noFilteredCarsFound"))
                .andExpect(model().attributeExists("wantedCategoryLevel"))
                .andExpect(model().attributeExists("wantedType"))
                .andExpect(model().attributeExists("wantedBrand"));

        mvc.perform(get("/cars/filter")
                .param("type", "ALL")
                .param("brand", "AUDI"))
                .andExpect(status().isOk())
                .andExpect(view().name("carcatalog"))
                .andExpect(model().attributeExists("noFilteredCarsFound"))
                .andExpect(model().attributeExists("wantedCategoryLevel"))
                .andExpect(model().attributeExists("wantedType"))
                .andExpect(model().attributeExists("wantedBrand"));
    }

    @Test
    public void carsAreFilteredByCategoryAndBrand() throws Exception {
        listOf4Cars();
        FunctionalLevel category = new FunctionalLevel();
        category.setFLevel(1);
        when(carRepository.findByCategoryAndBrand(category, Brand.AUDI)).thenReturn(listOf4Cars());
        when(functionalLevelRepository.findByFLevel(anyInt())).thenReturn(category);
        mvc.perform(get("/cars/filter")
                .param("categoryLevel", "0")
                .param("brand", "ALL"))
                .andExpect(status().isOk())
                .andExpect(view().name("carcatalog"))
                .andExpect(model().attributeExists("noFilteredCarsFound"))
                .andExpect(model().attributeExists("wantedCategoryLevel"))
                .andExpect(model().attributeExists("wantedType"))
                .andExpect(model().attributeExists("wantedBrand"));
    }

    @Test
    public void carsAreFilteredByCategoryAndType() throws Exception {
        listOf4Cars();
        FunctionalLevel category = new FunctionalLevel();
        category.setFLevel(1);
        when(carRepository.findByCategoryAndBrand(category, Brand.AUDI)).thenReturn(listOf4Cars());
        mvc.perform(get("/cars/filter")
                .param("categoryLevel", "1")
                .param("type", "ALL"))
                .andExpect(status().isOk())
                .andExpect(view().name("carcatalog"))
                .andExpect(model().attributeExists("noFilteredCarsFound"))
                .andExpect(model().attributeExists("wantedCategoryLevel"))
                .andExpect(model().attributeExists("wantedType"))
                .andExpect(model().attributeExists("wantedBrand"));
    }

    @Test
    public void carsAreFilteredByCategory() throws Exception {
        listOf4Cars();
        FunctionalLevel category = new FunctionalLevel();
        category.setFLevel(1);
        when(carRepository.findByCategory(category)).thenReturn(listOf4Cars());
        mvc.perform(get("/cars/filter")
                .param("categoryLevel", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("carcatalog"))
                .andExpect(model().attributeExists("noFilteredCarsFound"))
                .andExpect(model().attributeExists("wantedCategoryLevel"))
                .andExpect(model().attributeExists("wantedType"))
                .andExpect(model().attributeExists("wantedBrand"));
    }

    @Test
    public void carsAreFilteredByType() throws Exception {
        listOf4Cars();
        when(carRepository.findByType(CarType.BERLINE)).thenReturn(listOf4Cars());
        mvc.perform(get("/cars/filter")
                .param("type", "ALL"))
                .andExpect(status().isOk())
                .andExpect(view().name("carcatalog"))
                .andExpect(model().attributeExists("noFilteredCarsFound"))
                .andExpect(model().attributeExists("wantedCategoryLevel"))
                .andExpect(model().attributeExists("wantedType"))
                .andExpect(model().attributeExists("wantedBrand"));
    }

    @Test
    public void carsAreFilteredByBrand() throws Exception {
        listOf4Cars();
        when(carRepository.findByBrand(Brand.AUDI)).thenReturn(listOf4Cars());
        mvc.perform(get("/cars/filter")
                .param("brand", "ALL"))
                .andExpect(status().isOk())
                .andExpect(view().name("carcatalog"))
                .andExpect(model().attributeExists("noFilteredCarsFound"))
                .andExpect(model().attributeExists("wantedCategoryLevel"))
                .andExpect(model().attributeExists("wantedType"))
                .andExpect(model().attributeExists("wantedBrand"));

        mvc.perform(get("/cars/filter")
                .param("brand", "AUDI"))
                .andExpect(status().isOk())
                .andExpect(view().name("carcatalog"))
                .andExpect(model().attributeExists("cars"))
                .andExpect(model().attribute("cars", hasSize(4)));

        mvc.perform(get("/cars/filter")
                .param("brand", "AUDI")
                .param("type", "BERLINE"))
                .andExpect(status().isOk())
                .andExpect(view().name("carcatalog"))
                .andExpect(model().attributeExists("noFilteredCarsFound"))
                .andExpect(model().attributeExists("wantedCategoryLevel"))
                .andExpect(model().attributeExists("wantedType"))
                .andExpect(model().attributeExists("wantedBrand"));

        mvc.perform(get("/cars/filter")
                .param("brand", "ALL")
                .param("type", "BERLINE"))
                .andExpect(status().isOk())
                .andExpect(view().name("carcatalog"))
                .andExpect(model().attributeExists("noFilteredCarsFound"))
                .andExpect(model().attributeExists("wantedCategoryLevel"))
                .andExpect(model().attributeExists("wantedType"))
                .andExpect(model().attributeExists("wantedBrand"));
    }

    @Test
    public void loggedInUserCanAccessCardetailUrl() throws Exception {
        Long id = 1L;
        when(carRepository.findOne(id)).thenReturn(carOne);
        preformGetForAndExpectCarModelView("/cars/{id}", id, status().isOk());
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
