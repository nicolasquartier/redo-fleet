package com.realdolmen.fleet.mother;

import com.realdolmen.fleet.TestConfig;
import com.realdolmen.fleet.domain.FunctionalLevel;
import com.realdolmen.fleet.domain.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

// To use this class :
//      - Create a method calling the mother object, set the data, persist
//      - Add the new method in generateSampleData()
//      - Run the generateSampleDate() method
//      - Verify that the data was put in the db and close the application
//
//      NOTE: this runs against production DB!
@ContextConfiguration(classes = TestConfig.class)
@TestPropertySource(locations = "/application.properties")
@Transactional
@ActiveProfiles("TST")
@RunWith(SpringJUnit4ClassRunner.class)
public class SampleDataImporter {

    @Autowired
    private EntityManager entityManager;


    @Test
    public void generateSampleData() {
        generateDifferentRoleUsers();
        generateFunctionalLevels();
        // TODO: add new method here
    }

    private void generateFunctionalLevels() {
        for (int i = 1; i < 6; i++) {
            FunctionalLevel level = FunctionalLevelMother.init().build();
            level.setFLevel(i);
            entityManager.persist(level);
        }
    }

    private void generateDifferentRoleUsers() {
        User adminUser = UserMother.init().build();
        adminUser.setUsername("admin");
        adminUser.setAuthorities("ROLE_ADMIN");
        entityManager.persist(adminUser);

        User normalUser = UserMother.init().build();
        normalUser.setUsername("user");
        normalUser.setAuthorities("ROLE_USER");
        entityManager.persist(normalUser);

    }

    // TODO: implement method using mother and persist entity

}
