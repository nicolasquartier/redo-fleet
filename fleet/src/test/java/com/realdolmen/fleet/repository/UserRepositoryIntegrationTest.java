package com.realdolmen.fleet.repository;

import com.realdolmen.fleet.SpringBootTransactionalIntegrationTest;
import com.realdolmen.fleet.domain.User;
import com.realdolmen.fleet.mother.UserMother;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@SpringBootTransactionalIntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    private String expectedUsername;

    private User expectedUser;

    @Before
    public void init() {
        expectedUsername = "expected";

        expectedUser = saveUserWithUsername(expectedUsername);
        saveUserWithUsername(expectedUsername + "false");
        saveUserWithUsername("incorrect" + expectedUsername);
        saveUserWithUsername("random");
    }

    @Test
    public void findByUserNameGetsTheCorrectUsername() {
        User resultUser = userRepository.findByUsername(expectedUsername);
        assertEquals(expectedUsername, resultUser.getUsername());
        assertSame(expectedUser, resultUser);
    }

    private User saveUserWithUsername(String usernameToSet) {
        User user = UserMother.init().build();
        user.setUsername(usernameToSet);
        userRepository.save(user);
        return user;
    }
}
