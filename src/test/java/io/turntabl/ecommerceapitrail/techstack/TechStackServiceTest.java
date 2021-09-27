package io.turntabl.ecommerceapitrail.techstack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TechStackServiceTest {

    @Mock
    private TechStackRepository techStackRepositoryUnderTest;
    private TechStackService techStackServiceUnderTest;

    @BeforeEach
    void setUp() {
        techStackServiceUnderTest = new TechStackService(techStackRepositoryUnderTest);
    }

    @Test
    void canGetTechStack() {
        //when
        techStackServiceUnderTest.getTechStack();
        //then
        verify(techStackRepositoryUnderTest).findAll();
    }

    @Test
    void canAddTechStack() {
        //given
        TechStack techStack = new TechStack(
                "Java",
                Boolean.TRUE
        );
        //when
        techStackServiceUnderTest.addTechStack(techStack);
        //then
        ArgumentCaptor<TechStack> techStackArgumentCaptor = ArgumentCaptor.forClass((TechStack.class));

        verify(techStackRepositoryUnderTest).save(techStackArgumentCaptor.capture());
        TechStack capturedTechStack = techStackArgumentCaptor.getValue();

        assertEquals(capturedTechStack, techStack);
    }
}