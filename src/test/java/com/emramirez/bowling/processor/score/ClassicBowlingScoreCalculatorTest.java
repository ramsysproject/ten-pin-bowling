package com.emramirez.bowling.processor.score;

import com.emramirez.bowling.model.BowlingMatch;
import com.emramirez.bowling.model.BowlingResult;
import com.emramirez.bowling.model.Frame;
import com.emramirez.bowling.model.Player;
import com.emramirez.bowling.score.ClassicBowlingScoreCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ClassicBowlingScoreCalculatorTest {

    ClassicBowlingScoreCalculator calculator = new ClassicBowlingScoreCalculator();

    @Test
    public void apply_given_expected() {
        // arrange
        BowlingMatch bowlingMatch = new BowlingMatch();
        Map<Player, List<Frame>> playerGames = new HashMap<>();
        playerGames.put(buildPlayer(), buildFrames());
        bowlingMatch.setPlayerGames(playerGames);

        // act
        BowlingResult bowlingResult = calculator.apply(bowlingMatch);

        // assert
        assertNotNull(bowlingResult);
        assertEquals(167, bowlingResult.getPlayerResults().get(buildPlayer()).get(9).getScoreAt());
    }

    @Test
    public void apply_perfectFramesGiven_perfectScoreExpected() {
        // arrange
        BowlingMatch bowlingMatch = new BowlingMatch();
        Map<Player, List<Frame>> playerGames = new HashMap<>();
        playerGames.put(buildPlayer(), buildPerfectFrames());
        bowlingMatch.setPlayerGames(playerGames);

        // act
        BowlingResult bowlingResult = calculator.apply(bowlingMatch);

        // assert
        assertNotNull(bowlingResult);
        assertEquals(300, bowlingResult.getPlayerResults().get(buildPlayer()).get(9).getScoreAt());
        System.out.println(bowlingResult);
    }

    @Test
    public void apply_nearPerfectFramesGiven_299ScoreExpected() {
        // arrange
        BowlingMatch bowlingMatch = new BowlingMatch();
        Map<Player, List<Frame>> playerGames = new HashMap<>();
        playerGames.put(buildPlayer(), buildNearPerfectFrames());
        bowlingMatch.setPlayerGames(playerGames);

        // act
        BowlingResult bowlingResult = calculator.apply(bowlingMatch);

        // assert
        assertNotNull(bowlingResult);
        assertEquals(299, bowlingResult.getPlayerResults().get(buildPlayer()).get(9).getScoreAt());
        System.out.println(bowlingResult);
    }

    private Player buildPlayer() {
        return Player.builder().name("Emanuel").build();
    }

    private List<Frame> buildFrames() {
        List<Frame> frames = new ArrayList<>();
        frames.add(Frame.builder().round(1).firstPinFalls(10).build());
        frames.add(Frame.builder().round(2).firstPinFalls(7).secondPinFalls(3).build());
        frames.add(Frame.builder().round(3).firstPinFalls(9).secondPinFalls(0).build());
        frames.add(Frame.builder().round(4).firstPinFalls(10).build());
        frames.add(Frame.builder().round(5).firstPinFalls(0).secondPinFalls(8).build());
        frames.add(Frame.builder().round(6).firstPinFalls(8).secondPinFalls(2).build());
        frames.add(Frame.builder().round(7).firstPinFalls(0).secondPinFalls(6).build());
        frames.add(Frame.builder().round(8).firstPinFalls(10).build());
        frames.add(Frame.builder().round(9).firstPinFalls(10).build());
        frames.add(Frame.builder().round(10).firstPinFalls(10).secondPinFalls(8).thirdPinFalls(1).build());

        return frames;
    }

    private List<Frame> buildPerfectFrames() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            frames.add(Frame.builder().round(i).firstPinFalls(10).build());
        }
        frames.add(Frame.builder().round(10).firstPinFalls(10).secondPinFalls(10).thirdPinFalls(10).build());

        return frames;
    }

    private List<Frame> buildNearPerfectFrames() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            frames.add(Frame.builder().round(i).firstPinFalls(10).build());
        }
        frames.add(Frame.builder().round(10).firstPinFalls(10).secondPinFalls(10).thirdPinFalls(9).build());

        return frames;
    }
}
