package com.realdolmen.fleet.domain;

import com.realdolmen.fleet.mother.AuthoritiesMother;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AuthoritiesTest {

    private Authorities authorities;

    @Before
    public void init() {
        authorities = AuthoritiesMother.init().build();
    }

    @Test
    public void id() throws Exception {
        Long expected = 666L;
        authorities.setId(expected);
        assertNotNull(authorities.getId());
        assertEquals(expected, authorities.getId());
    }


    @Test
    public void version() throws Exception {
        Long expected = 666L;
        authorities.setVersion(expected);
        assertNotNull(authorities.getVersion());
        assertEquals(expected, authorities.getVersion());
    }


    @Test
    public void username() throws Exception {
        String expected = "username";
        authorities.setUsername(expected);
        assertNotNull(authorities.getUsername());
        assertEquals(expected, authorities.getUsername());
    }


    @Test
    public void authority() throws Exception {
        String expected = "DUMMY_ROLE";
        authorities.setAuthority(expected);
        assertNotNull(authorities.getAuthority());
        assertEquals(expected, authorities.getAuthority());
    }

}