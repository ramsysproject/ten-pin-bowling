package com.emramirez.bowling.processor.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ScoreLineValidatorTest {

    ScoreLineValidator scoreLineValidator = new ScoreLineValidator();

    @Test
    public void test_validScoreLineGiven_trueExpected() {
        // arrange
        String scoreLine = "Emanuel 10";

        // act
        boolean result = scoreLineValidator.test(scoreLine);

        // assert
        assertTrue(result);
    }
}
