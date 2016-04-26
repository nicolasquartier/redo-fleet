package com.realdolmen.fleet.domain;

import com.realdolmen.fleet.mother.UserMother;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    private User user;

    @Mock
    private FunctionalLevel functionalLevel;

    @Before
    public void init() {
        this.user = UserMother.init().build();
    }

    @Test
    public void id() throws Exception {
        Long expected = 666L;
        user.setId(expected);
        assertNotNull(user.getId());
        assertEquals(expected, user.getId());
    }

    @Test
    public void version() throws Exception {
        Long expected = 666L;
        user.setVersion(expected);
        assertEquals(expected, user.getVersion());
    }

    @Test
    public void firstname() {
        String expected = "expected";
        user.setFirstName(expected);
        assertSame(expected, user.getFirstName());
    }


    @Test
    public void lastname() {
        String expected = "expected";
        user.setLastName(expected);
        assertSame(expected, user.getLastName());
    }


    @Test
    public void username() {
        String expected = "expected";
        user.setUsername(expected);
        assertSame(expected, user.getUsername());
    }


    @Test
    public void email() {
        String expected = "expected@expected.be";
        user.setEmail(expected);
        assertSame(expected, user.getEmail());
    }


    @Test
    public void category() {
        user.setFunctionalLevel(functionalLevel);
        assertSame(functionalLevel, user.getFunctionalLevel());
    }


    @Test
    public void password() {
        String expected = "expected";
        user.setPassword(expected);
        assertSame(expected, user.getPassword());
    }

    @Test
    public void businessUnit() {
        String expected = "expected";
        user.setBusinessUnit(expected);
        assertSame(expected, user.getBusinessUnit());
    }

    @Test
    public void enabled() {
        Boolean expected = false;
        user.setEnabled(expected);
        assertSame(expected, user.getEnabled());
    }
}