package com.emramirez.bowling.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FrameTest {

    public static final String PIN_SUM_IS_INCORRECT = "Pin sum is incorrect";
    public static final String SHOULD_BE_STRIKE = "Should be strike";
    public static final String SHOULD_NOT_BE_STRIKE = "Should not be strike";
    public static final String SHOULD_BE_SPARE = "Should be spare";
    public static final String SHOULD_NOT_BE_SPARE = "Should not be spare";

    @Test
    public void isSpare_strikeGiven_falseExpected() {
        // arrange
        Frame frame = Frame.builder().firstPinFalls(10).build();

        // act
        boolean isSpare = frame.isSpare();

        // assert
        assertFalse(SHOULD_NOT_BE_SPARE, isSpare);
    }

    @Test
    public void isSpare_lessThan10PinsFallenGiven_falseExpected() {
        // arrange
        Frame frame = Frame.builder()
                .firstPinFalls(5)
                .secondPinFalls(3).build();

        // act
        boolean isSpare = frame.isSpare();

        // assert
        assertFalse(SHOULD_NOT_BE_SPARE, isSpare);
    }

    @Test
    public void isSpare_10PinsFallenInTwoShotsGiven_trueExpected() {
        // arrange
        Frame frame = Frame.builder()
                .firstPinFalls(3)
                .secondPinFalls(7).build();

        // act
        boolean isSpare = frame.isSpare();

        // assert
        assertTrue(SHOULD_BE_SPARE, isSpare);
    }

    @Test
    public void isStrike_10PinsFallenInFirstShotGiven_trueExpected() {
        // arrange
        Frame frame = Frame.builder()
                .firstPinFalls(10).build();

        // act
        boolean isStrike = frame.isStrike();

        // assert
        assertTrue(SHOULD_BE_STRIKE, isStrike);
    }

    @Test
    public void isStrike_10PinsFallenInFirstShotGiven_falseExpected() {
        // arrange
        Frame frame = Frame.builder()
                .firstPinFalls(3).build();

        // act
        boolean isStrike = frame.isStrike();

        // assert
        assertFalse(SHOULD_NOT_BE_STRIKE, isStrike);
    }

    @Test
    public void sumFallenPins_oneShotGiven_properSumExpected() {
        // arrange
        Frame frame = Frame.builder()
                .firstPinFalls(5).build();

        // act
        int result = frame.sumFallenPins();

        // assert
        assertEquals(PIN_SUM_IS_INCORRECT, 5, result);
    }

    @Test
    public void sumFallenPins_twoShotsGiven_properSumExpected() {
        // arrange
        Frame frame = Frame.builder()
                .firstPinFalls(5)
                .secondPinFalls(3).build();

        // act
        int result = frame.sumFallenPins();

        // assert
        assertEquals(PIN_SUM_IS_INCORRECT, 8, result);
    }

    @Test
    public void sumFallenPins_threeShotsGiven_properSumExpected() {
        // arrange
        Frame frame = Frame.builder()
                .firstPinFalls(5)
                .secondPinFalls(3)
                .thirdPinFalls(8).build();

        // act
        int result = frame.sumFallenPins();

        // assert
        assertEquals(PIN_SUM_IS_INCORRECT, 16, result);
    }

    @Test
    public void sumRegularPlay_oneShotGiven_properSumExpected() {
        // arrange
        Frame frame = Frame.builder()
                .firstPinFalls(5).build();

        // act
        int result = frame.sumRegularPlay();

        // assert
        assertEquals(PIN_SUM_IS_INCORRECT, 5, result);
    }

    @Test
    public void sumRegularPlay_twoShotsGiven_properSumExpected() {
        // arrange
        Frame frame = Frame.builder()
                .firstPinFalls(5)
                .secondPinFalls(5).build();

        // act
        int result = frame.sumRegularPlay();

        // assert
        assertEquals(PIN_SUM_IS_INCORRECT, 10, result);
    }

    @Test
    public void sumRegularPlay_threeShotsGiven_firstTwoShotsSumExpected() {
        // arrange
        Frame frame = Frame.builder()
                .firstPinFalls(5)
                .secondPinFalls(5)
                .thirdPinFalls(10).build();

        // act
        int result = frame.sumRegularPlay();

        // assert
        assertEquals(PIN_SUM_IS_INCORRECT, 10, result);
    }

    @Test
    public void getFallenPinsAsList_threeShotsGiven_listExpected() {
        // arrange
        Frame frame = Frame.builder()
                .firstPinFalls(1)
                .secondPinFalls(2)
                .thirdPinFalls(3).build();

        // act
        List<Integer> result = frame.getFallenPinsAsList();

        // assert
        assertEquals((Integer) 1, result.get(0));
        assertEquals((Integer) 2, result.get(1));
        assertEquals((Integer) 3, result.get(2));
    }
}
