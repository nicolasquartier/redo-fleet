package com.realdolmen.fleet.security;

import com.realdolmen.fleet.Application;
import com.realdolmen.fleet.service.AuthService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.asm.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ContextConfiguration(classes = Application.class)
@ActiveProfiles("TST")
@WebAppConfiguration(value = "resources/templates/")
public abstract class AbstractSecurityTest {

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


     void preformGetForAndExpect(String path, ResultMatcher noUser, ResultMatcher normalUser, ResultMatcher adminUser) throws Exception {
       mvc.perform(get(path))
                .andExpect(noUser);
        mvc.perform(get(path).with(user("user").roles("USER")))
                .andExpect(normalUser);
        mvc.perform(get(path).with(user("user").roles("ADMIN")))
                .andExpect(adminUser);
    }


    void preformGetForAndExpectView(String path, String expectedView) throws Exception {
        mvc.perform(get(path))
                .andExpect(view().name(expectedView));
        mvc.perform(get(path).with(user("user").roles("USER")))
                .andExpect(view().name(expectedView));
        mvc.perform(get(path).with(user("user").roles("ADMIN")))
                .andExpect(view().name(expectedView));
    }

    void preformGetWithCSRFForAndExpect(String path, ResultMatcher noUser, ResultMatcher normalUser, ResultMatcher adminUser) throws Exception {
        mvc.perform(get(path))
                .andExpect(noUser);
        mvc.perform(get(path).with(user("user").roles("USER")).with(csrf()))
                .andExpect(normalUser);
        mvc.perform(get(path).with(user("user").roles("ADMIN")).with(csrf()))
                .andExpect(adminUser);
    }
}
