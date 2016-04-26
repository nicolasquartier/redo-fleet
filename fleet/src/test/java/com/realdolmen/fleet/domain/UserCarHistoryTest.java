package com.realdolmen.fleet.domain;

import com.realdolmen.fleet.mother.UserCarHistoryMother;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserCarHistoryTest {

    private UserCarHistory userCarHistory;

    @Mock
    private CompanyCar companyCar;

    @Mock
    private User user;

    @Before
    public void init() {
        userCarHistory = UserCarHistoryMother.init().build();
    }

    @Test
    public void id() throws Exception {
        Long expected = 666L;
        userCarHistory.setId(expected);
        assertNotNull(userCarHistory.getId());
        assertEquals(expected, userCarHistory.getId());
    }

    @Test
    public void version() throws Exception {
        Long expected = 666L;
        userCarHistory.setVersion(expected);
        assertEquals(expected, userCarHistory.getVersion());
    }

    @Test
    public void startDate() {
        LocalDate expected = LocalDate.now();
        userCarHistory.setStartDate(expected);
        assertSame(expected, userCarHistory.getStartDate());
    }

    @Test
    public void endDate() {
        LocalDate expected = LocalDate.now();
        userCarHistory.setEndDate(expected);
        assertSame(expected, userCarHistory.getEndDate());
    }

    @Test
    public void replacement() {
        Boolean expected = false;
        userCarHistory.setReplacement(expected);
        assertSame(expected, userCarHistory.getReplacement());
    }

    @Test
    public void choosenLevel() {
        Integer expected = 1;
        userCarHistory.setChoosenLevel(expected);
        assertSame(expected, userCarHistory.getChoosenLevel());
    }

    @Test
    public void functionalLevelCopy() {
        Integer expected = 1;
        userCarHistory.setFunctionalLevelCopy(expected);
        assertSame(expected, userCarHistory.getFunctionalLevelCopy());
    }


    @Test
    public void estimatedMileage() {
        Integer expected = 66_666;
        userCarHistory.setEstimatedMileage(expected);
        assertSame(expected, userCarHistory.getEstimatedMileage());
    }

    @Test
    public void user() {
        userCarHistory.setUser(user);
        assertSame(user, userCarHistory.getUser());
    }

    @Test
    public void companyCar() {
        userCarHistory.setCompanyCar(companyCar);
        assertSame(companyCar, userCarHistory.getCompanyCar());
    }
}