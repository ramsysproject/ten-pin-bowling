package com.emramirez.bowling.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BowlingMatch {
    private Map<Player, List<Frame>> playerGames;
}
