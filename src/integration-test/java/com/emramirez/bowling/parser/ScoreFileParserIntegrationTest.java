package com.emramirez.bowling.parser;

import com.emramirez.bowling.BowlingApplication;
import com.emramirez.bowling.model.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BowlingApplication.class,
        initializers = ConfigFileApplicationContextInitializer.class)
public class ScoreFileParserIntegrationTest {

    @Autowired
    ScoreFileParser scoreFileParser;

    @Test
    public void parseScoreFile_validFileGiven_playersFramesMapExpected() throws URISyntaxException, IOException {
        // arrange
        Path path = Paths.get(ClassLoader.getSystemResource("games/demogame.txt").toURI());
        Stream<String> scoreLines = Files.lines(path);

        // act
        Map playerFrames = scoreFileParser.parseScoreLines(scoreLines);

        // assert
        assertTrue(playerFrames.containsKey(buildPlayer("Jeff")));
        assertTrue(playerFrames.containsKey(buildPlayer("John")));
    }

    private Player buildPlayer(String name) {
        return Player.builder().name(name).build();
    }

}