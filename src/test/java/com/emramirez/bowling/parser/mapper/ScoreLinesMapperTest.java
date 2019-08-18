package com.emramirez.bowling.parser.mapper;

import com.emramirez.bowling.model.Frame;
import com.emramirez.bowling.model.factory.FrameFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ScoreLinesMapperTest {

    public static final String WAS_EXPECTING_A_STRIKE = "Was expecting a strike";
    public static final String WAS_NOT_EXPECTING_A_STRIKE = "Was not expecting a strike";
    public static final String WAS_NOT_EXPECTING_A_SPARE = "Was not expecting a spare";
    public static final String WAS_EXPECTING_A_SPARE = "Was expecting a spare";

    ScoreLinesMapper scoreLinesMapper = new ScoreLinesMapper(new FrameFactory());

    @Test
    public void map_perfectScoresGiven_tenStrikeFramesExpected() {
        // arrange
        List<Integer> scoreLines = IntStream.range(1, 13).map(i -> 10).boxed().collect(toList());

        // act
        List<Frame> frames = scoreLinesMapper.map(scoreLines);

        // assert
        frames.stream().forEach(frame -> {
            assertTrue(WAS_EXPECTING_A_STRIKE, frame.isStrike());
        });
    }

    @Test
    public void map_2Strikes2SpareScoresGiven_properFramesExpected() {
        // arrange
        List<Integer> scoreLines = new ArrayList<>();
        addStrikes(scoreLines, 2);
        addRegular(scoreLines, 6);
        addSpare(scoreLines, 2);
        addLastRoundSpare(scoreLines);

        // act
        List<Frame> frames = scoreLinesMapper.map(scoreLines);

        // assert
        assertTrue(WAS_EXPECTING_A_STRIKE, frames.get(0).isStrike());
        assertTrue(WAS_EXPECTING_A_STRIKE, frames.get(1).isStrike());
        assertFalse(WAS_NOT_EXPECTING_A_STRIKE, frames.get(4).isStrike());
        assertFalse(WAS_NOT_EXPECTING_A_SPARE, frames.get(4).isSpare());
        assertTrue(WAS_EXPECTING_A_SPARE, frames.get(8).isSpare());
        assertTrue(WAS_EXPECTING_A_SPARE, frames.get(9).isSpare());
    }

    @Test
    public void map_regularRoundsGiven_noStrikesOrSparesExpected() {
        // arrange
        List<Integer> scoreLines = new ArrayList<>();
        addRegular(scoreLines, 10);

        // act
        List<Frame> frames = scoreLinesMapper.map(scoreLines);

        // assert
        frames.stream().forEach(frame -> {
            assertFalse(WAS_NOT_EXPECTING_A_STRIKE, frame.isStrike());
            assertFalse(WAS_NOT_EXPECTING_A_SPARE, frame.isSpare());
        });
    }

    @Test
    public void map_regularLastRoundGiven_noThirdShotExpected() {
        // arrange
        List<Integer> scoreLines = new ArrayList<>();
        addRegular(scoreLines, 10);

        // act
        List<Frame> frames = scoreLinesMapper.map(scoreLines);

        // assert
        assertEquals("No third shot score expected", 0, frames.get(9).getThirdPinFalls());
    }

    private void addStrikes(List<Integer> scoreLines, int quantity) {
        scoreLines.addAll(IntStream.range(1, ++quantity).map(i -> 10).boxed().collect(toList()));
    }

    private void addSpare(List<Integer> scoreLines, int quantity) {
        for (int i = 0; i < quantity; i++) {
            scoreLines.add(3);
            scoreLines.add(7);
        }
    }

    private void addLastRoundSpare(List<Integer> scoreLines) {
        scoreLines.add(3);
        scoreLines.add(7);
        scoreLines.add(5);
    }

    private void addRegular(List<Integer> scoreLines, int quantity) {
        for (int i = 0; i < quantity; i++) {
            scoreLines.add(5);
            scoreLines.add(4);
        }
    }
}