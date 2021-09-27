package io.turntabl.ecommerceapitrail.techstack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TechStackRepositoryTest {

    @Autowired
    private TechStackRepository techStackRepositoryUnderTest;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        techStackRepositoryUnderTest.deleteAll();
    }

    @Test
    void canFindTechStackByName() {
        //given
        String name = "JUnit5" ;
        TechStack testFramework = new TechStack(
                name,
                Boolean.TRUE
        );

        techStackRepositoryUnderTest.save(testFramework);
        Optional<TechStack> expected = techStackRepositoryUnderTest.findTechStackByName(name);
        //when
        techStackRepositoryUnderTest.findTechStackByName(name);
        //then
        assertTrue(expected.isPresent());
    }
    @Test
    void itShouldCheckIfFindTechStackByNameIsNotPresent() {
        //given
        String name = "JUnit5" ;
        TechStack testFramework = new TechStack(
                name,
                Boolean.TRUE
        );
        Optional<TechStack> expected = techStackRepositoryUnderTest.findTechStackByName(name);
        //when
        techStackRepositoryUnderTest.findTechStackByName(name);
        //then
        assertFalse(expected.isPresent());
    }
}