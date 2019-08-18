package com.emramirez.bowling.presenter;

import com.emramirez.bowling.model.BowlingResult;
import com.emramirez.bowling.model.FrameScore;
import com.emramirez.bowling.model.Player;
import com.emramirez.bowling.util.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class BowlingResultPresenterTest {

    BowlingResultPresenter presenter = new BowlingResultPresenter();

    @Test
    public void present_resultsGiven_correctFormatExpected() throws URISyntaxException, IOException {
        // arrange
        Map<Player, List<FrameScore>> playerGames = buildPlayerGames();
        BowlingResult bowlingResult = BowlingResult.builder().playerResults(playerGames).build();
        Path path = Paths.get(ClassLoader.getSystemResource("results/single_player_presentation.txt").toURI());
        String expectedRepresentation = Files.lines(path)
                .map(this::stripSpaces)
                .collect(Collectors.joining());

        //
        String result = presenter.present(bowlingResult);

        // assert
        log.info(result);
        assertEquals("Representation mismatch", expectedRepresentation, stripSpaces(result));
    }

    private Map buildPlayerGames() {
        Map<Player, List<FrameScore>> playerGames = new HashMap<>();
        Player player = Player.builder().name("Emanuel").build();
        List<FrameScore> frames = TestUtils.buildFrameScores();
        playerGames.put(player, frames);

        return playerGames;
    }

    private String stripSpaces(String line) {
        return line.replaceAll("\\s+", "");
    }
}