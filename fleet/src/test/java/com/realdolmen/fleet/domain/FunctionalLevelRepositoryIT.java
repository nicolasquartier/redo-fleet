package com.realdolmen.fleet.domain;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.realdolmen.fleet.SpringBootTransactionalIntegrationTest;
import com.realdolmen.fleet.repositories.FunctionalLevelRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import java.util.List;


@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@DatabaseSetup(FunctionalLevelRepositoryIT.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL , value = { FunctionalLevelRepositoryIT.DATASET })
@DirtiesContext
@SpringBootTransactionalIntegrationTest
public class FunctionalLevelRepositoryIT {

    protected static final String DATASET = "classpath:datasets/it-functionallevels.xml";

    @Autowired
    private FunctionalLevelRepository repository;

    @Test
    public void findAllShouldReturnFiveLevels() {

        List<FunctionalLevel> levels = repository.findAll();
        Assert.assertEquals (5,levels.size());
    }

    @Test
    public void SaveOneShouldReturnSix() {
        List<FunctionalLevel> levels = repository.findAll();
        Assert.assertEquals (5,levels.size());
        FunctionalLevel level = new FunctionalLevel();
        level.setFLevel(6);
        repository.save(level);
        Assert.assertEquals (6,repository.findAll().size());
    }



}
