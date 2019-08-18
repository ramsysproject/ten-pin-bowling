package com.emramirez.bowling.parser.extractor;

import com.emramirez.bowling.model.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ScoreLinePlayerExtractorTest {

    private ScoreLinePlayerExtractor extractor = new ScoreLinePlayerExtractor();

    @Test
    public void extract_validInputGiven_playerWithNameExpected() {
        // arrange
        String input = "Tommy 5";

        // act
        Player player = extractor.extract(input);

        // assert
        assertEquals("Player name mismatch", "Tommy", player.getName());
    }
}
