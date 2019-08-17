package com.emramirez.bowling.processor;

import com.emramirez.bowling.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ScoreFileParser {

    private final ScoreLinePlayerExtractor scoreLinePlayerExtractor;

    public Map parseScoreLines(Stream<String> scoreLines) {
        Map<Player, List<Integer>> playerFrames = scoreLines
                .filter(not(String::isEmpty))
                .collect(
                    Collectors.groupingBy(
                        scoreLinePlayerExtractor::extract,
                        mapping(ScoreFileParser::extractPins, toList())
                    )
                );

        System.out.println(playerFrames);

        return playerFrames;
    }

    private static Integer extractPins(String scoreLine) {
        return Arrays.stream(scoreLine.split("\\s+"))
                .skip(1)
                .map(s -> s.replace("F", "0"))
                .findFirst()
                .map(Integer::valueOf).get();
    }
}
