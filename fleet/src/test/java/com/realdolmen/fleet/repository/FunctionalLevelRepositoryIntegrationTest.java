package com.realdolmen.fleet.repository;

import com.realdolmen.fleet.SpringBootTransactionalIntegrationTest;
import com.realdolmen.fleet.domain.FunctionalLevel;
import com.realdolmen.fleet.mother.FunctionalLevelMother;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTransactionalIntegrationTest
public class FunctionalLevelRepositoryIntegrationTest {


    @Autowired
    private FunctionalLevelRepository functionalLevelRepository;

    @Before
    public void init() {
        List<FunctionalLevel> levels = listOfFourLevels();
        IntStream.range(1,5).forEach(levelIntToSet -> {
            FunctionalLevel level = levels.get(levelIntToSet - 1);
            level.setFLevel(levelIntToSet);
        });
        levels.forEach(functionalLevelRepository::save);
    }

    private List<FunctionalLevel> listOfFourLevels() {
        FunctionalLevel one, two, three, four;
        one = FunctionalLevelMother.init().build();
        two = FunctionalLevelMother.init().build();
        three = FunctionalLevelMother.init().build();
        four = FunctionalLevelMother.init().build();
        return Arrays.asList(one, two, three, four);
    }

    @Test
    public void findOneByIdExpectFunctionalLevelWithSameId() {
        FunctionalLevel functionalLevel = FunctionalLevelMother.init().build();
        assertNull(functionalLevel.getId());
        functionalLevelRepository.save(functionalLevel);
        assertNotNull(functionalLevel.getId());
        assertSame(functionalLevelRepository.findOne(functionalLevel.getId()), functionalLevel);
    }

    @Test
    public void findByFLevelExpectAllForFLevel() {
        IntStream.range(1,4).forEach(expectedLevel -> {
            FunctionalLevel resultLevelObj = functionalLevelRepository.findByFLevel(expectedLevel);
            assertEquals(resultLevelObj.getFLevel(), new Integer(expectedLevel));
        });
    }

}
