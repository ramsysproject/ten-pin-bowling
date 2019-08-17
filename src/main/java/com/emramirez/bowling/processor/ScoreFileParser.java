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

    private final ScoreLinePlayerExtractor playerExtractor;
    private final ScoreLinePinExtractor pinExtractor;

    public Map parseScoreLines(Stream<String> scoreLines) {
        Map<Player, List<Integer>> playerFrames =
                scoreLines
                        .filter(not(String::isEmpty))
                        .collect(Collectors.groupingBy(playerExtractor::extract, mapping(pinExtractor::extract, toList()))
                        );

        System.out.println(playerFrames);

        return playerFrames;
    }
}
