package com.emramirez.bowling;

import com.emramirez.bowling.model.Player;
import com.emramirez.bowling.processor.ScoreFileParser;
import com.emramirez.bowling.processor.ScoreLinePlayerExtractor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScoreFileParserTest {

    public static final String PLAYER_NAME = "Emanuel";
    @Mock
    ScoreLinePlayerExtractor scoreLinePlayerExtractor;
    @InjectMocks
    private ScoreFileParser scoreFileParser;

    @Test
    public void parseScoreFile_validFileGiven_playersFramesMapExpected() throws URISyntaxException, IOException {
        // arrange
        Stream<String> scoreLines = Stream.of("Emanuel 0", "Emanuel 10");
        when(scoreLinePlayerExtractor.extract(anyString())).thenReturn(Player.builder().name(PLAYER_NAME).build());

        // act
        Map playerFrames = scoreFileParser.parseScoreLines(scoreLines);

        // assert
        assertTrue(playerFrames.containsKey(buildPlayer(PLAYER_NAME)));
    }

    private Player buildPlayer(String name) {
        return Player.builder().name(name).build();
    }

}