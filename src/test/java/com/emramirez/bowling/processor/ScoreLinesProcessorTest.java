package com.emramirez.bowling.processor;

import com.emramirez.bowling.model.Frame;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ScoreLinesProcessorTest {

    ScoreLinesProcessor scoreLinesProcessor = new ScoreLinesProcessor();

    @Test
    public void process_validScoresGiven_tenFramesExpected() {
        // arrange
        List<Integer> scoreLines = IntStream.range(1, 13).map(i -> 10).boxed().collect(toList());

        // act
        List<Frame> frames = scoreLinesProcessor.process(scoreLines);

        // assert
        assertEquals("Amount of frames is invalid", 10, frames.size());
    }
}