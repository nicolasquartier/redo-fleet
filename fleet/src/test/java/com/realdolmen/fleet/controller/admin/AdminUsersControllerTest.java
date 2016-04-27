package com.realdolmen.fleet.controller.admin;

import com.realdolmen.fleet.domain.User;
import com.realdolmen.fleet.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration(value = "resources/templates/")
public class AdminUsersControllerTest {

    private MockMvc mvc;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AdminUsersController adminUsersController;

    @Mock
    private User userOne, userTwo, userThree;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders
                .standaloneSetup(adminUsersController)
                .build();

        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(userOne, userTwo, userThree));
    }

    @Test
    public void preformAdminUsers() throws Exception {
        mvc.perform(get("/admin/users"))
                .andExpect(view().name("admin/allusers"))
                .andExpect(model().attribute("users", hasItem(userOne)))
                .andExpect(model().attribute("users", hasItem(userTwo)))
                .andExpect(model().attribute("users", hasItem(userThree))
                );
    }
}