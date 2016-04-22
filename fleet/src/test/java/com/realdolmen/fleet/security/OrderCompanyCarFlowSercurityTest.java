package com.realdolmen.fleet.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class OrderCompanyCarFlowSercurityTest extends AbstractSecurityTest {

    @Test
    public void carConfigureViewUrlsAreOpenForUserAndAdmin() throws Exception {
        performGetForNoUserAndExpect("/carConfigure", status().is3xxRedirection());
    }


}
