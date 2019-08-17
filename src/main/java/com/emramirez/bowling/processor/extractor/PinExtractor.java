package com.emramirez.bowling.processor.extractor;

import com.emramirez.bowling.model.Player;

@FunctionalInterface
public interface PinExtractor {

    Integer extract(String input);
}
