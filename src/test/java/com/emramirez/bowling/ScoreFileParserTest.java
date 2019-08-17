package com.emramirez.bowling;

import com.emramirez.bowling.model.Player;
import com.emramirez.bowling.processor.ScoreFileParser;
import com.emramirez.bowling.processor.ScoreLinePinExtractor;
import com.emramirez.bowling.processor.ScoreLinePlayerExtractor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
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
    @Mock
    ScoreLinePinExtractor scoreLinePinExtractor;
    @InjectMocks
    private ScoreFileParser scoreFileParser;

    @Test
    public void parseScoreFile_validLinesGiven_playersFramesMapExpected() throws URISyntaxException, IOException {
        // arrange
        Stream<String> scoreLines = Stream.of("Emanuel 5");
        when(scoreLinePlayerExtractor.extract(anyString())).thenReturn(Player.builder().name(PLAYER_NAME).build());
        when(scoreLinePinExtractor.extract(anyString())).thenReturn(5);

        // act
        Map<Player, List<Integer>> playerFrames = scoreFileParser.parseScoreLines(scoreLines);

        // assert
        Player player = buildPlayer(PLAYER_NAME);
        assertTrue(playerFrames.containsKey(player));
        assertTrue(playerFrames.get(player).equals(Arrays.asList(5)));
    }

    private Player buildPlayer(String name) {
        return Player.builder().name(name).build();
    }

}