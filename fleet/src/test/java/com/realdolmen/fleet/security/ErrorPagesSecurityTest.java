package com.realdolmen.fleet.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
public class ErrorPagesSecurityTest extends AbstractSecurityTest {

    @Test
    public void pageNotFoundPageUrlsAreOpen() throws Exception {
        performGetForAndExpect("/404", status().isOk(), status().isOk(), status().isOk());
    }

    @Test
    public void forbiddenUrlPageUrlsAreOpen() throws Exception {
        performGetForAndExpect("/403", status().isOk(), status().isOk(), status().isOk());
    }


}
