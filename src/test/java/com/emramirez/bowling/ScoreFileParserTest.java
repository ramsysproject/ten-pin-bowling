package com.emramirez.bowling;

import com.emramirez.bowling.model.Player;
import com.emramirez.bowling.processor.ScoreFileParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ScoreFileParserTest {

    private ScoreFileParser scoreFileParser = new ScoreFileParser();

    @Test
    public void parseScoreFile_validFileGiven_playersFramesMapExpected() throws URISyntaxException, IOException {
        // arrange
        Path path = Paths.get(ClassLoader.getSystemResource("testgame.txt").toURI());

        // act
        Map playerFrames = scoreFileParser.parseScoreFile(path);

        // assert
        assertTrue(playerFrames.containsKey(buildPlayer("Jeff")));
        assertTrue(playerFrames.containsKey(buildPlayer("John")));
    }

    private Player buildPlayer(String jeff) {
        return Player.builder().name(jeff).build();
    }

}