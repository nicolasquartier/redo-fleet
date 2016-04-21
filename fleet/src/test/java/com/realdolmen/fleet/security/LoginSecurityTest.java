package com.realdolmen.fleet.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoginSecurityTest extends AbstractSecurityTest{

    @Test
    public void loginUrlIsOpen() throws Exception {
        preformGetForAndExpect("/login", status().isOk(), status().isOk(), status().isOk());
        preformGetForAndExpectView("/login", "login");
    }


    @Test
    public void loginErrorUrlIsOpen() throws Exception {
        preformGetForAndExpect("/login?error", status().isOk(), status().isOk(), status().isOk());
        preformGetForAndExpectView("/login?error", "login");
    }

    @Test
    public void logoutRedirectsToLogin() throws Exception {
        String requestPath = "/login?logout";
        preformGetWithCSRFForAndExpect(requestPath, status().isOk(), status().isOk(), status().isOk());
        preformGetForAndExpectView("/login?logout", "login");
    }

}
