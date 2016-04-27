package com.realdolmen.fleet.domain;

import com.realdolmen.fleet.mother.FunctionalLevelMother;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class FunctionalLevelTest {

    FunctionalLevel level;

    @Before
    public void init() {
        level = FunctionalLevelMother.init().build();
    }


    @Test
    public void id() throws Exception {
        Long expected = 666L;
        level.setId(expected);
        assertNotNull(level.getId());
        assertEquals(expected, level.getId());
    }

    @Test
    public void version() throws Exception {
        Long expected = 666L;
        level.setVersion(expected);
        assertEquals(expected, level.getVersion());
    }

    @Test
    public void category() {
        Integer expected = 666;
        level.setFLevel(expected);
        assertEquals(expected, level.getFLevel());
    }


}