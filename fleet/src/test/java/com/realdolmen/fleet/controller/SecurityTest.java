package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@ActiveProfiles("TST")
@WebAppConfiguration(value = "resources/templates/")
public class SecurityTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void init() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(springSecurityFilterChain)
                .build();
    }

    @Test
    public void adminCanAccesAdminUrls() throws Exception {
        preformGetForAndExpect("/admin", status().is3xxRedirection(), status().isForbidden(), status().isOk());
    }

    @Test
    public void rootUrlsAreOpen() throws Exception {
        preformGetForAndExpect("/", status().is3xxRedirection(), status().is3xxRedirection(), status().is3xxRedirection());
    }

    @Test
    public void pageNotFoundPageUrlsAreOpen() throws Exception {
        preformGetForAndExpect("/404", status().isOk(), status().isOk(), status().isOk());
    }

    @Test
    public void forbiddenUrlPageUrlsAreOpen() throws Exception {
        preformGetForAndExpect("/403", status().isOk(), status().isOk(), status().isOk());
    }


    @Test
    public void loginUrlIsOpen() throws Exception {
        preformGetForAndExpect("/login", status().isOk(), status().isOk(), status().isOk());
        preformGetForAndExpectView("/login", "login");
    }


    @Test
    public void loggedInUserCanAccessCarsUrl() throws Exception {
        preformGetForAndExpect("/cars/", status().is3xxRedirection(), status().isOk(), status().isOk());
    }

    @Test
    public void loggedInUserCanAccessCarConfigureUrl() throws Exception {
        preformGetForAndExpect("/cars/1/configure", status().is3xxRedirection(), status().isOk(), status().isOk());
    }

    @Test
    public void loggedInUserCanAccessMyCarUrl() throws Exception {
        //user who is logged in can acces cars/mycar
        //fleet admin can't access any cars/mycar
        preformGetForAndExpect("/cars/mycar", status().is3xxRedirection(), status().isOk(), status().isForbidden());
    }

    @Test
    public void logoutRedirectsToLogin() throws Exception {
        String requestPath = "/logout";
        preformPostWithCSRFForAndExpect(requestPath, status().is3xxRedirection(), status().is3xxRedirection(), status().is3xxRedirection());
    }

    private void preformGetForAndExpect(String path, ResultMatcher noUser, ResultMatcher normalUser, ResultMatcher adminUser) throws Exception {
        mvc.perform(get(path))
                .andExpect(noUser);
        mvc.perform(get(path).with(user("user").roles("USER")))
                .andExpect(normalUser);
        mvc.perform(get(path).with(user("user").roles("ADMIN")))
                .andExpect(adminUser);
    }

    private void preformGetForAndExpectView(String path, String expectedView) throws Exception {
        mvc.perform(get(path))
                .andExpect(view().name(expectedView));
        mvc.perform(get(path).with(user("user").roles("USER")))
                .andExpect(view().name(expectedView));
        mvc.perform(get(path).with(user("user").roles("ADMIN")))
                .andExpect(view().name(expectedView));
    }

    private void preformPostWithCSRFForAndExpect(String path, ResultMatcher noUser, ResultMatcher normalUser, ResultMatcher adminUser) throws Exception {
        mvc.perform(get(path))
                .andExpect(noUser);
        mvc.perform(get(path).with(user("user").roles("USER")).with(csrf()))
                .andExpect(normalUser);
        mvc.perform(get(path).with(user("user").roles("ADMIN")).with(csrf()))
                .andExpect(adminUser);
    }

}
