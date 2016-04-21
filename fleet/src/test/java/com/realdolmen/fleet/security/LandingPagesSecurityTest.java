package com.realdolmen.fleet.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class LandingPagesSecurityTest extends AbstractSecurityTest{


    @Test
    public void adminCanAccesAdminUrls() throws Exception {
        preformGetForAndExpect("/admin", status().is3xxRedirection(), status().isForbidden(), status().isOk());
    }

    @Test
    public void rootUrlsAreOpen() throws Exception {
        preformGetForAndExpect("/", status().is3xxRedirection(), status().is3xxRedirection(), status().is3xxRedirection());
    }
}
