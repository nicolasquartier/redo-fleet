package com.realdolmen.fleet.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class CarsSecurityTest extends AbstractSecurityTest{

    @Test
    public void loggedInUserCanAccessCarsUrl() throws Exception {
        preformGetForAndExpect("/cars/", status().is3xxRedirection(), status().isOk(), status().isOk());
    }

    @Test
    public void loggedInUserCanAccessMyCarUrl() throws Exception {
        preformGetForAndExpect("/cars/mycar", status().is3xxRedirection(), status().isOk(), status().isForbidden());
    }
}
