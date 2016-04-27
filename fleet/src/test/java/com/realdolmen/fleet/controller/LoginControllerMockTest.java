package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.domain.Authorities;
import com.realdolmen.fleet.domain.User;
import com.realdolmen.fleet.repository.AuthoritiesRepository;
import com.realdolmen.fleet.service.impl.AuthServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration(value = "resources/templates/")
public class LoginControllerMockTest {

    @Mock
    private AuthoritiesRepository authoritiesRepository;

    @Mock
    private Authorities auth;

    @Mock
    private AuthServiceImpl authentication;

    @InjectMocks
    private LoginController loginController = new LoginController();

    private MockMvc mvc;
    @Mock
    private User user;

    @Before
    public void init() {
        mvc = MockMvcBuilders
                .standaloneSetup(loginController)
                .setSingleView(new InternalResourceView(""))
                .build();

        when(authentication.getName()).thenReturn("user");
        when(authoritiesRepository.findByUsername("user")).thenReturn(auth);
        when(auth.getAuthority()).thenReturn("");
        when(authentication.getCurrentUser()).thenReturn(user);
        when(user.getFirstName()).thenReturn("first name");
        when(user.getLastName()).thenReturn("last name");
        when(user.getCategory()).thenReturn(666);
    }

    @Test
    public void callLoginWithErrorParameterAndExpectALoginError() throws Exception {
        mvc.perform(get("/login?error=true"))
                .andExpect(model().attributeExists("loginError"))
                .andExpect(model().attribute("loginError", equalTo("Invalid username or password")))
        ;
    }

    @Test
    public void callLoginWithRoleAdminAndExpectAdminLandingPage() throws Exception {
        when(auth.getAuthority()).thenReturn("ROLE_ADMIN");
        mvc.perform(get("/login"))
                .andExpect(view().name("redirect:/admin"))
        ;
    }


    @Test
    public void callLoginWithRoleUserAndExpectUserLandingPage() throws Exception {
        when(auth.getAuthority()).thenReturn("ROLE_USER");
        mvc.perform(get("/login"))
                .andExpect(view().name("redirect:/cars"))
        ;
    }

    @Test
    public void callLoginWithLogoutParameterAndExpectALogoutMessage() throws Exception {
        mvc.perform(get("/login?logout=true"))
                .andExpect(model().attributeExists("logoutMessage"))
                .andExpect(model().attribute("logoutMessage", equalTo("Successfully logged out!")))
        ;
    }
}