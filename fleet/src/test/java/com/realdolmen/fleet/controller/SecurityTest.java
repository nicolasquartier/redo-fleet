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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class )
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
        preformGetForAndExpect("/", status().isOk(), status().isOk(), status().isOk());
    }


    @Test
    public void loginUrlIsOpen() throws Exception {
           preformGetForAndExpect("/login", status().isOk(), status().isOk(), status().isOk());
    }

    private void preformGetForAndExpect(String path, ResultMatcher noUser, ResultMatcher normalUser, ResultMatcher adminUser) throws Exception {
        mvc.perform(get(path))
                .andExpect(noUser);
        mvc.perform(get(path).with(user("user").roles("USER")))
                .andExpect(normalUser);
        mvc.perform(get(path).with(user("user").roles("ADMIN")))
                .andExpect(adminUser);
    }






}
