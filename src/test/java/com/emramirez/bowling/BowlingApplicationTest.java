package com.emramirez.bowling;

import com.emramirez.bowling.parser.ScoreFileParser;
import com.emramirez.bowling.presenter.TextBowlingResultPresenter;
import com.emramirez.bowling.score.BowlingScoreCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BowlingApplicationTest {

    @Mock
    ScoreFileParser scoreFileParser;
    @Mock
    BowlingScoreCalculator bowlingScoreCalculator;
    @Mock
    TextBowlingResultPresenter textBowlingResultPresenter;
    @InjectMocks
    private BowlingApplication bowlingApplication;

    @Test(expected = IllegalArgumentException.class)
    public void run_noArgsGiven_exceptionExpected() throws IOException {
        // act
        bowlingApplication.run();

        // assert - done in annotation
    }

    @Test
    public void run_filepathGiven_executionsExpected() throws URISyntaxException, IOException {
        // arrange
        Path path = Paths.get(ClassLoader.getSystemResource("results/single_player_presentation.txt").toURI());

        // act
        bowlingApplication.run(path.toString());

        // assert
        verify(scoreFileParser).parseScoreLines(any());
        verify(bowlingScoreCalculator).apply(any());
        verify(textBowlingResultPresenter).present(any());
    }

}
