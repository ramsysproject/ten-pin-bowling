package com.emramirez.bowling.parser;

import com.emramirez.bowling.model.Frame;
import com.emramirez.bowling.model.Player;
import com.emramirez.bowling.parser.extractor.ScoreLinePinExtractor;
import com.emramirez.bowling.parser.extractor.ScoreLinePlayerExtractor;
import com.emramirez.bowling.parser.mapper.ScoreLinesMapper;
import com.emramirez.bowling.parser.validator.ScoreLineValidator;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScoreFileParserTest {

    public static final String PLAYER_NAME = "Emanuel";
    @Mock
    ScoreLinePlayerExtractor scoreLinePlayerExtractor;
    @Mock
    ScoreLinePinExtractor scoreLinePinExtractor;
    @Mock
    ScoreLineValidator scoreLineValidator;
    @Mock
    ScoreLinesMapper scoreLinesMapper;
    @InjectMocks
    private ScoreFileParser scoreFileParser;

    @Test
    public void parseScoreFile_validLinesGiven_playersFramesMapExpected() throws URISyntaxException, IOException {
        // arrange
        Stream<String> scoreLines = Stream.of("Emanuel 5");
        Player player = Player.builder().name(PLAYER_NAME).build();
        Frame frame = Frame.builder().firstPinFalls(5).build();
        when(scoreLinePlayerExtractor.extract(anyString())).thenReturn(player);
        when(scoreLinePinExtractor.extract(anyString())).thenReturn(5);
        when(scoreLineValidator.test(anyString())).thenReturn(true);
        when(scoreLinesMapper.map(any())).thenReturn(Arrays.asList(frame));

        // act
        Map<Player, List<Frame>> playerFrames = scoreFileParser.parseScoreLines(scoreLines);

        // assert
        assertTrue(playerFrames.containsKey(player));
        assertEquals(5, playerFrames.get(player).get(0).getFirstPinFalls());
    }

    @Test
    public void parseScoreFile_noLinesGiven_emptyMapExpected() throws URISyntaxException, IOException {
        // arrange
        Stream<String> scoreLines = Stream.empty();

        // act
        Map<Player, List<Integer>> playerFrames = scoreFileParser.parseScoreLines(scoreLines);

        // assert
        assertEquals(0, playerFrames.size());
    }

    private Player buildPlayer(String name) {
        return Player.builder().name(name).build();
    }

}