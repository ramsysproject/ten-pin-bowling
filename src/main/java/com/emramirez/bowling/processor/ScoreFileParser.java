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

public class ScoreFileParser {

    public Map parseScoreFile(Path scoreFilesPath) throws IOException {
        Stream<String> scoreLines = Files.lines(scoreFilesPath);

        Map<Player, List<String>> playerFrames = scoreLines
                .filter(not(String::isEmpty))
                .collect(Collectors.groupingBy((String scoreLine) -> {
                    return Arrays.stream(scoreLine.split("\\s+")).findFirst().map(Player::new).get();
                }));

        System.out.println(playerFrames);

        return playerFrames;
    }
}
