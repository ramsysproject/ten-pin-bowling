package com.emramirez.bowling.parser.extractor;

@FunctionalInterface
public interface PinExtractor {

    Integer extract(String input);
}
