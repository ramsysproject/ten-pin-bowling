package com.emramirez.bowling.processor;

import com.emramirez.bowling.model.Frame;
import com.emramirez.bowling.model.Player;
import com.emramirez.bowling.processor.extractor.ScoreLinePinExtractor;
import com.emramirez.bowling.processor.extractor.ScoreLinePlayerExtractor;
import com.emramirez.bowling.processor.validator.ScoreLineValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor
public class ScoreFileParser {

    private final ScoreLinePlayerExtractor playerExtractor;
    private final ScoreLinePinExtractor pinExtractor;
    private final ScoreLineValidator scoreLineValidator;
    private final ScoreLinesProcessor scoreLinesProcessor;

    public Map parseScoreLines(Stream<String> scoreLines) {
        Map<Player, List<Integer>> playerScores =
                scoreLines
                        .filter(scoreLineValidator::test)
                        .collect(
                                groupingBy(playerExtractor::extract, mapping(pinExtractor::extract, toList()))
                        );

        Map<Player, List<Frame>> playerFrames =
                playerScores.entrySet().stream()
                        .collect(toMap(
                                e -> e.getKey(),
                                e -> scoreLinesProcessor.process(e.getValue()))
                        );

        return playerFrames;
    }
}
