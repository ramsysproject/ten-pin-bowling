package com.emramirez.bowling.parser.extractor;

import com.emramirez.bowling.model.Player;

@FunctionalInterface
public interface PlayerExtractor {

    Player extract(String input);
}
