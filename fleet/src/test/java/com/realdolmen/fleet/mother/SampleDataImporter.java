package com.realdolmen.fleet.mother;

import com.realdolmen.fleet.TestConfig;
import com.realdolmen.fleet.config.SecurityConfig;
import com.realdolmen.fleet.domain.Authorities;
import com.realdolmen.fleet.domain.FunctionalLevel;
import com.realdolmen.fleet.domain.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

// To use this class :
//      - Create a method calling the mother object, set the data, persist
//      - Add the new method in generateSampleData()
//      - Run the generateSampleDate() method (place ignore in comment - don't forget to put it on again)
//      - Verify that the data was put in the db and close the application
//
//      NOTE: this runs against production DB!
@ContextConfiguration(classes = {TestConfig.class, SecurityConfig.class})
@TestPropertySource(locations = "/application.properties")
@Transactional
@ActiveProfiles("TST")
@RunWith(SpringJUnit4ClassRunner.class)
public class SampleDataImporter {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Ignore
    public void generateSampleData() {
        generateDifferentRoleUsers();
        generateAuthoritiesForDiffrentRoles();
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
        adminUser.setPassword(passwordEncoder.encode("123"));
        adminUser.setEnabled(true);
        adminUser.setEmail("admin@realdolmen.com");
        entityManager.persist(adminUser);

        User normalUser = UserMother.init().build();
        normalUser.setUsername("user");
        normalUser.setPassword(passwordEncoder.encode("123"));
        normalUser.setEmail("user@realdolmen.com");
        entityManager.persist(normalUser);
    }


    private void generateAuthoritiesForDiffrentRoles() {
        Authorities adminRole = AuthoritiesMother.init().build();
        adminRole.setUsername("admin");
        adminRole.setAuthority("ROLE_ADMIN");
        entityManager.persist(adminRole);

        Authorities normal = AuthoritiesMother.init().build();
        normal.setUsername("user");
        normal.setAuthority("ROLE_USER");
        entityManager.persist(normal);
    }

    // TODO: implement method using mother and persist entity

}
