package com.emramirez.bowling.parser.validator;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class ScoreLineValidator implements Predicate<String> {

    Pattern scoreLinePattern = Pattern.compile("\\w+\\s+(F|10|[0-9]){1}$");

    @Override
    public boolean test(String input) {
        return scoreLinePattern.matcher(input).matches();
    }
}
