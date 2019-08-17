package com.emramirez.bowling.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class BowlingResult {
    private Map<Player, List<FrameScore>> playerResults;
}
