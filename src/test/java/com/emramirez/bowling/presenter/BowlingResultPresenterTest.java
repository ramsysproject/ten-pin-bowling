package com.emramirez.bowling.presenter;

import com.emramirez.bowling.model.BowlingResult;
import com.emramirez.bowling.model.FrameScore;
import com.emramirez.bowling.model.Player;
import com.emramirez.bowling.util.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class BowlingResultPresenterTest {

    BowlingResultPresenter presenter = new BowlingResultPresenter();

    @Test
    public void present_resultsGiven_correctFormatExpected() {
        // arrange
        Map<Player, List<FrameScore>> playerGames = buildPlayerGames();
        BowlingResult bowlingResult = BowlingResult.builder().playerResults(playerGames).build();

        //
        String result = presenter.present(bowlingResult);

        // assert
        assertNotNull(result);
        System.out.println(result);
    }

    private Map buildPlayerGames() {
        Map<Player, List<FrameScore>> playerGames = new HashMap<>();
        Player player = Player.builder().name("Emanuel").build();
        List<FrameScore> frames = TestUtils.buildFrameScores();
        playerGames.put(player, frames);

        return playerGames;
    }
}