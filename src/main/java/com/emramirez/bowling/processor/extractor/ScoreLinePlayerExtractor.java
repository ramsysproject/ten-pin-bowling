package com.emramirez.bowling.processor.extractor;

import com.emramirez.bowling.model.Player;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ScoreLinePlayerExtractor implements PlayerExtractor {

    @Override
    public Player extract(String input) {
        return Arrays.stream(input.split("\\s+"))
                .findFirst()
                .map(Player::new).get();
    }
}
