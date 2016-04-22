package com.realdolmen.fleet.repository;

import com.realdolmen.fleet.SpringBootTransactionalIntegrationTest;
import com.realdolmen.fleet.domain.Authorities;
import com.realdolmen.fleet.mother.AuthoritiesMother;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTransactionalIntegrationTest
public class AuthoritiesRepositoryIntegrationTest {

    private Authorities admin;

    private Authorities user;

    // SUT
    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Before
    public void init() {
        this.admin = AuthoritiesMother.init().build();
        this.admin.setAuthority("ROLE_ADMIN");
        this.admin.setUsername("admin");
        authoritiesRepository.save(admin);

        this.user = AuthoritiesMother.init().build();
        this.user.setAuthority("ROLE_USERNAME");
        this.user.setUsername("user");
        authoritiesRepository.save(user);
    }

    @Test
    public void findByUsernameReturnsOnlyCorrectUser() {
        Authorities adminResult = authoritiesRepository.findByUsername("admin");
        assertAuthorities(this.admin, adminResult);

        Authorities userResult = authoritiesRepository.findByUsername("user");
        assertAuthorities(this.user, userResult);
    }

    private void assertAuthorities(Authorities expected, Authorities result) {
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getAuthority(), result.getAuthority());
        assertEquals(expected.getUsername(), result.getUsername());
    }


}
