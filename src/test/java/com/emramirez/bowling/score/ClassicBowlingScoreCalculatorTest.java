package com.emramirez.bowling.score;

import com.emramirez.bowling.model.BowlingMatch;
import com.emramirez.bowling.model.BowlingResult;
import com.emramirez.bowling.model.Frame;
import com.emramirez.bowling.model.Player;
import com.emramirez.bowling.model.factory.FrameScoreFactory;
import com.emramirez.bowling.util.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ClassicBowlingScoreCalculatorTest {

    ClassicBowlingScoreCalculator calculator = new ClassicBowlingScoreCalculator(new FrameScoreFactory());

    @Test
    public void apply_167ScoreGameGiven_correctResultExpected() {
        // arrange
        BowlingMatch bowlingMatch = new BowlingMatch();
        Map<Player, List<Frame>> playerGames = new HashMap<>();
        Player player = TestUtils.buildPlayer("John");
        playerGames.put(player, TestUtils.build167GameFrames());
        bowlingMatch.setPlayerGames(playerGames);

        // act
        BowlingResult bowlingResult = calculator.apply(bowlingMatch);

        // assert
        assertEquals(167, bowlingResult.getPlayerResults().get(player).get(9).getScoreAt());
    }

    @Test
    public void apply_perfectFramesGiven_perfectScoreExpected() {
        // arrange
        BowlingMatch bowlingMatch = new BowlingMatch();
        Map<Player, List<Frame>> playerGames = new HashMap<>();
        Player player = TestUtils.buildPlayer("John");
        playerGames.put(player, TestUtils.buildPerfectFrames());
        bowlingMatch.setPlayerGames(playerGames);

        // act
        BowlingResult bowlingResult = calculator.apply(bowlingMatch);

        // assert
        assertNotNull(bowlingResult);
        assertEquals(300, bowlingResult.getPlayerResults().get(player).get(9).getScoreAt());
        System.out.println(bowlingResult);
    }

    @Test
    public void apply_nearPerfectFramesGiven_299ScoreExpected() {
        // arrange
        BowlingMatch bowlingMatch = new BowlingMatch();
        Map<Player, List<Frame>> playerGames = new HashMap<>();
        Player player = TestUtils.buildPlayer("John");
        playerGames.put(player, TestUtils.buildNearPerfectFrames());
        bowlingMatch.setPlayerGames(playerGames);

        // act
        BowlingResult bowlingResult = calculator.apply(bowlingMatch);

        // assert
        assertNotNull(bowlingResult);
        assertEquals(299, bowlingResult.getPlayerResults().get(player).get(9).getScoreAt());
        System.out.println(bowlingResult);
    }
}
