package com.emramirez.bowling.processor;

import com.emramirez.bowling.model.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class ScoreFileParser {

    public Map parseScoreFile(Path scoreFilesPath) throws IOException {
        Stream<String> scoreLines = Files.lines(scoreFilesPath);

        Map<Player, List<Integer>> playerFrames = scoreLines
                .filter(not(String::isEmpty))
                .collect(
                    Collectors.groupingBy(
                        ScoreFileParser::extractPlayer,
                        mapping(ScoreFileParser::extractPins, toList())
                    )
                );

        System.out.println(playerFrames);

        return playerFrames;
    }

    private static Player extractPlayer(String scoreLine) {
        return Arrays.stream(scoreLine.split("\\s+")).findFirst().map(Player::new).get();
    }

    private static Integer extractPins(String scoreLine) {
        return Arrays.stream(scoreLine.split("\\s+"))
                .skip(1)
                .map(s -> s.replace("F", "0"))
                .findFirst()
                .map(Integer::valueOf).get();
    }
}
