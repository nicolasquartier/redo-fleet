package com.realdolmen.fleet.repository;

import com.realdolmen.fleet.SpringBootTransactionalIntegrationTest;
import com.realdolmen.fleet.domain.CompanyCar;
import com.realdolmen.fleet.domain.User;
import com.realdolmen.fleet.domain.UserCarHistory;
import com.realdolmen.fleet.mother.UserCarHistoryMother;
import com.realdolmen.fleet.mother.UserMother;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.stream.IntStream;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

@SpringBootTransactionalIntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserCarHistoryRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCarHistoryRepository userCarHistoryRepository;

    @Mock
    private CompanyCar randomCompanyCar;

    @Mock
    private User expectedUser;

    @Before
    public void init() {
//        save3RandomUsers();
//        addRandomHistoryObjectsToExistingUsers();

//        expectedUser = UserMother.init().build();
//        userRepository.save(expectedUser);
    }

    @Test
    public void findByUsernameAndEndDateAfterReturnsOnlyForCorrectUser() {
        UserCarHistory expectedHistory = UserCarHistoryMother.init().build();
        expectedHistory.setCompanyCar(randomCompanyCar);
        expectedHistory.setUser(expectedUser);
        userCarHistoryRepository.save(expectedHistory);

        UserCarHistory resultHistory = userCarHistoryRepository.findByUserAndEndDateAfter(expectedUser, new Date());

        assertNotNull(resultHistory);
        assertSame(expectedHistory, resultHistory);
    }

    private void save3RandomUsers() {
        IntStream.range(0, 2).forEach(index -> {
            User user = UserMother.init().build();
            user.setUsername("random" + index);
            userRepository.save(user);
        });
    }

    private void addRandomHistoryObjectsToExistingUsers() {
        Iterable<User> users = userRepository.findAll();
        users.forEach(user -> {
            UserCarHistory history = UserCarHistoryMother.init().build();
            history.setUser(user);
            history.setCompanyCar(randomCompanyCar);
            userCarHistoryRepository.save(history);
        });
    }
}
