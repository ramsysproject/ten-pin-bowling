package com.emramirez.bowling.score;

import com.emramirez.bowling.BowlingApplication;
import com.emramirez.bowling.model.BowlingMatch;
import com.emramirez.bowling.model.BowlingResult;
import com.emramirez.bowling.model.Frame;
import com.emramirez.bowling.model.Player;
import com.emramirez.bowling.util.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BowlingApplication.class,
        initializers = ConfigFileApplicationContextInitializer.class)
public class ClassicBowlingScoreCalculatorIntegrationTest {

    @Autowired
    ClassicBowlingScoreCalculator calculator;

    @Test
    public void apply_167ScoreGameGiven_correctResultExpected() {
        // arrange
        BowlingMatch bowlingMatch = new BowlingMatch();
        Map<Player, List<Frame>> playerGames = new HashMap<>();
        Player player = TestUtils.buildPlayer("Tommy");
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
        Player player = TestUtils.buildPlayer("Tommy");
        playerGames.put(player, TestUtils.buildPerfectFrames());
        bowlingMatch.setPlayerGames(playerGames);

        // act
        BowlingResult bowlingResult = calculator.apply(bowlingMatch);

        // assert
        assertEquals(300, bowlingResult.getPlayerResults().get(player).get(9).getScoreAt());
    }

    @Test
    public void apply_nearPerfectFramesGiven_299ScoreExpected() {
        // arrange
        BowlingMatch bowlingMatch = new BowlingMatch();
        Map<Player, List<Frame>> playerGames = new HashMap<>();
        Player player = TestUtils.buildPlayer("Tommy");
        playerGames.put(player, TestUtils.buildNearPerfectFrames());
        bowlingMatch.setPlayerGames(playerGames);

        // act
        BowlingResult bowlingResult = calculator.apply(bowlingMatch);

        // assert
        assertEquals(299, bowlingResult.getPlayerResults().get(player).get(9).getScoreAt());
    }

    @Test
    public void apply_zeroScoreFramesGiven_zeroScoreExpected() {
        // arrange
        BowlingMatch bowlingMatch = new BowlingMatch();
        Map<Player, List<Frame>> playerGames = new HashMap<>();
        Player player = TestUtils.buildPlayer("Tommy");
        playerGames.put(player, TestUtils.buildZeroScoreFrames());
        bowlingMatch.setPlayerGames(playerGames);

        // act
        BowlingResult bowlingResult = calculator.apply(bowlingMatch);

        // assert
        assertEquals(0, bowlingResult.getPlayerResults().get(player).get(9).getScoreAt());
    }
}
