package com.emramirez.bowling.parser.validator;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ScoreLineValidatorTest {

    ScoreLineValidator scoreLineValidator = new ScoreLineValidator();

    @Test
    public void test_validScoreLineGiven_patternMatchExpected() {
        // arrange
        String scoreLine = "Emanuel 10";

        // act
        boolean result = scoreLineValidator.test(scoreLine);

        // assert
        assertTrue(result);
    }

    @Test
    public void test_scoreLineWithExtraSpacesGiven_spacesStripAndPatternMatchExpected() {
        // arrange
        String scoreLine = "Emanuel    5";

        // act
        boolean result = scoreLineValidator.test(scoreLine);

        // assert
        assertTrue(result);
    }

    @Test
    public void test_faultScoreLineGiven_patternMatchExpected() {
        // arrange
        String scoreLine = "Emanuel F";

        // act
        boolean result = scoreLineValidator.test(scoreLine);

        // assert
        assertTrue(result);
    }

    @ParameterizedTest
    @MethodSource("provideScoreLines")
    public void test_multipleInvalidScoreLinesGiven_noPatternMatchExpected(String input, boolean expected) {
        // assert
        assertEquals(expected, scoreLineValidator.test(input));
    }

    private static Stream<Arguments> provideScoreLines() {
        return Stream.of(
                Arguments.of("Emanuel E", false),
                Arguments.of("Emanuel 100", false),
                Arguments.of("Emanuel 10 E", false),
                Arguments.of("Emanuel 9 F", false),
                Arguments.of("5 Emanuel 9", false),
                Arguments.of("5 Emanuel", false),
                Arguments.of("Emanuel", false),
                Arguments.of("5", false),
                Arguments.of("  ", false)
        );
    }
}
