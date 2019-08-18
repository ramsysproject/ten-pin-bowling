package com.emramirez.bowling.parser;

import com.emramirez.bowling.model.Frame;
import com.emramirez.bowling.model.Player;
import com.emramirez.bowling.parser.extractor.ScoreLinePinExtractor;
import com.emramirez.bowling.parser.extractor.ScoreLinePlayerExtractor;
import com.emramirez.bowling.parser.mapper.ScoreLinesMapper;
import com.emramirez.bowling.parser.validator.ScoreLineValidator;
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
    private final ScoreLinesMapper scoreLinesMapper;

    public Map parseScoreLines(Stream<String> scoreLines) {
        Map<Player, List<Frame>> playerFrames =
                scoreLines
                        .filter(scoreLineValidator::test)
                        .collect(
                                groupingBy(playerExtractor::extract, mapping(pinExtractor::extract, toList()))
                        ).entrySet().stream()
                        .collect(
                                toMap(e -> e.getKey(), e -> scoreLinesMapper.map(e.getValue()))
                        );

        return playerFrames;
    }
}
