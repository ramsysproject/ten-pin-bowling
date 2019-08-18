package com.emramirez.bowling.parser.mapper;

import com.emramirez.bowling.model.Frame;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ScoreLinesMapperTest {

    ScoreLinesMapper scoreLinesMapper = new ScoreLinesMapper();

    @Test
    public void map_validScoresGiven_tenFramesExpected() {
        // arrange
        List<Integer> scoreLines = IntStream.range(1, 13).map(i -> 10).boxed().collect(toList());

        // act
        List<Frame> frames = scoreLinesMapper.map(scoreLines);

        // assert
        assertEquals("Amount of frames is invalid", 10, frames.size());
    }
}