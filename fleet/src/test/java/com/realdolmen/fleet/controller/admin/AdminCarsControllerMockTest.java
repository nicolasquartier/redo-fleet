package com.realdolmen.fleet.controller.admin;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.FunctionalLevel;
import com.realdolmen.fleet.repository.CarRepository;
import com.realdolmen.fleet.repository.FunctionalLevelRepository;
import com.realdolmen.fleet.utils.FileUtil;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Errors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
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
    private FunctionalLevelRepository functionalLevelRepository;

    @Mock
    private Car carOne, carTwo, carThree, carFour;

    @Mock
    private FunctionalLevel functionalLevel;

    @InjectMocks
    private AdminCarsController adminCarsController;

    @Before
    public void init() {
        mvc = MockMvcBuilders
                .standaloneSetup(adminCarsController)
                .build();

        when(carRepository.findAll()).thenReturn(listOf4Cars());
        when(functionalLevelRepository.findAll()).thenReturn(Collections.singletonList(functionalLevel));
        when(functionalLevel.getFLevel()).thenReturn(66);
        when(functionalLevel.getId()).thenReturn(66L);
        adminCarsController.setFunctionalLevelRepository(functionalLevelRepository);
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
        MockMultipartFile fileMock = new MockMultipartFile("thumbnail", "randomfilename", "multipart/form-data", this.getClass().getResourceAsStream("/test/emptytest.jpg"));
        mvc.perform(MockMvcRequestBuilders.fileUpload("/admin/cars/666").file(fileMock))
                .andExpect(redirectedUrl("/admin/cars"));
    }

    @Test(expected = FileNotFoundException.class)
    @Ignore
    public void shouldProcessAddCar() throws Exception {
        MockMultipartFile fileMock = new MockMultipartFile("thumbnail", "randomfilename", "multipart/form-data", this.getClass().getResourceAsStream("/test/emptytest.jpg"));
        mvc.perform(MockMvcRequestBuilders.fileUpload("/admin/cars/add").file(fileMock))
                .andExpect(redirectedUrl("/admin/cars"));
    }

    // @TODO NEVER WORKED
    @Test
    @Ignore
    public void preformPostWithErrorsAndExpectReturnToView() throws Exception {
        Errors errorsMock = Mockito.mock(Errors.class);
        Mockito.when(errorsMock.getErrorCount()).thenReturn(5);
        mvc.perform(post("/admin/cars/5")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .requestAttr("car", carFour)
                .requestAttr("errors", errorsMock))
                .andExpect(view().name("/admin/caredit"));
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
