package com.realdolmen.fleet.repository;

import com.realdolmen.fleet.SpringBootTransactionalIntegrationTest;
import com.realdolmen.fleet.domain.FunctionalLevel;
import com.realdolmen.fleet.mother.FunctionalLevelMother;
import com.realdolmen.fleet.repositories.FunctionalLevelRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTransactionalIntegrationTest
public class FunctionalLevelRepositoryIntegrationTest {

    private FunctionalLevel functionalLevel;

    @Autowired
    private FunctionalLevelRepository functionalLevelRepository;

    @Before
    public void init() {
        this.functionalLevel = FunctionalLevelMother.init().build();
    }

    @Test
    public void shouldReturnFunctionalLevelId() {
        assertNull(functionalLevel.getId());
        functionalLevelRepository.save(functionalLevel);
        assertNotNull(functionalLevel.getId());
        assertSame(functionalLevelRepository.findOne(functionalLevel.getId()), functionalLevel);
    }

}
