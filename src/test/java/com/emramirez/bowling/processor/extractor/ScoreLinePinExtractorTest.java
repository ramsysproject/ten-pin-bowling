package com.emramirez.bowling.processor.extractor;

import com.emramirez.bowling.processor.extractor.ScoreLinePinExtractor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ScoreLinePinExtractorTest {

    @InjectMocks
    ScoreLinePinExtractor scoreLinePinExtractor;

    @Test
    public void extract_validInputGiven_pinExpected() {
        // arrange
        String line = "Emanuel 5";

        // act
        Integer result = scoreLinePinExtractor.extract(line);

        // assert
        assertEquals((Integer) 5, result);
    }

    @Test(expected = NumberFormatException.class)
    public void extract_invalidInputGiven_illegalArgumentExceptionExpected() {
        // arrange
        String line = "5 Emanuel";

        // act
        scoreLinePinExtractor.extract(line);

        // assert - done in annotation
    }

    @Test(expected = NumberFormatException.class)
    public void extract_emptyInputGiven_illegalArgumentExceptionExpected() {
        // arrange
        String line = "";

        // act
        scoreLinePinExtractor.extract(line);

        // assert - done in annotation
    }
}
