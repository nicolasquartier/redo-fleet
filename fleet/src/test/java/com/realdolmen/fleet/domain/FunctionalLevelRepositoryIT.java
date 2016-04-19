package com.realdolmen.fleet.domain;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.realdolmen.fleet.SpringBootTransactionalIntegrationTest;
import com.realdolmen.fleet.mother.FunctionalLevelMother;
import com.realdolmen.fleet.repositories.FunctionalLevelRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.persistence.EntityManager;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;


@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
//@DatabaseSetup(FunctionalLevelRepositoryIT.DATASET)
//@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL , value = { FunctionalLevelRepositoryIT.DATASET })
@DirtiesContext
@SpringBootTransactionalIntegrationTest
public class FunctionalLevelRepositoryIT {

    //    protected static final String DATASET = "classpath:datasets/it-functionallevels.xml";    private Car car;
    private FunctionalLevelMother functionalLevelMother;
    private FunctionalLevel functionalLevel;

    @Autowired
    private FunctionalLevelRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Before
    public void init() {
        this.functionalLevelMother = new FunctionalLevelMother();
        this.functionalLevel = this.functionalLevelMother.build();
    }

    @Test
    public void shouldReturnFunctionalLevelId() {
        entityManager.persist(this.functionalLevel);
        assertNotNull(this.functionalLevel.getId());
        assertEquals(new Long(1L), this.functionalLevel.getId());
    }

    @Test
    @Ignore
    public void findAllShouldReturnFiveLevels() {

        List<FunctionalLevel> levels = repository.findAll();
        assertEquals(5, levels.size());
    }

    @Test
    @Ignore
    public void SaveOneShouldReturnSix() {
        List<FunctionalLevel> levels = repository.findAll();
        assertEquals(5, levels.size());
        FunctionalLevel level = new FunctionalLevel();
        level.setFLevel(6);
        repository.save(level);
        assertEquals(6, repository.findAll().size());
    }


}
