package com.emramirez.bowling.parser.extractor;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ScoreLinePinExtractor implements PinExtractor {

    public static final String FAULT = "F";
    public static final String ZERO = "0";
    public static final String SPACES_REGEX = "\\s+";

    @Override
    public Integer extract(String input) {
        return Arrays.stream(input.split(SPACES_REGEX))
                .skip(1)
                .map(s -> s.replace(FAULT, ZERO))
                .findFirst()
                .map(Integer::valueOf).get();
    }
}
