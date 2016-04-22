package com.realdolmen.fleet.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoginSecurityTest extends AbstractSecurityTest{

    @Test
    public void loginUrlIsOpen() throws Exception {
        performGetForAndExpect("/login", status().isOk(), status().isOk(), status().isOk());
        performGetForAndExpectView("/login", "login");
    }


    @Test
    public void loginErrorUrlIsOpen() throws Exception {
        performGetForAndExpect("/login?error", status().isOk(), status().isOk(), status().isOk());
        performGetForAndExpectView("/login?error", "login");
    }

    @Test
    public void logoutRedirectsToLogin() throws Exception {
        String requestPath = "/login?logout";
        performGetWithCSRFForAndExpect(requestPath, status().isOk(), status().isOk(), status().isOk());
        performGetForAndExpectView("/login?logout", "login");
    }

}
