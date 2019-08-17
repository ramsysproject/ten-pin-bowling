package com.emramirez.bowling.processor;

import com.emramirez.bowling.model.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
@SpringBootTest
public class ScoreFileParserIT {

    @Autowired
    ScoreFileParser scoreFileParser;

    @Test
    public void parseScoreFile_validFileGiven_playersFramesMapExpected() throws URISyntaxException, IOException {
        // arrange
        Path path = Paths.get(ClassLoader.getSystemResource("demogame.txt").toURI());
        Stream<String> scoreLines = Files.lines(path);

        // act
        Map playerFrames = scoreFileParser.parseScoreLines(scoreLines);

        // assert
        assertTrue(playerFrames.containsKey(buildPlayer("Jeff")));
        assertTrue(playerFrames.containsKey(buildPlayer("John")));
        System.out.println(playerFrames);
    }

    private Player buildPlayer(String name) {
        return Player.builder().name(name).build();
    }

}