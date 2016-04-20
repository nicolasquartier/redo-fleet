package com.realdolmen.fleet.domain;


import com.realdolmen.fleet.SpringBootTransactionalIntegrationTest;
import com.realdolmen.fleet.mother.OptionMother;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTransactionalIntegrationTest
public class OptionRepositoryIT {
    private Option option;

    @Autowired
    private EntityManager entityManager;

    @Before
    public void init() {
        this.option = OptionMother.init().build();
    }

    @Test
    public void getIdTest() {
        entityManager.persist(option);
        assertNotNull(option.getId());
    }
}
