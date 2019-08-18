package com.emramirez.bowling.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FrameScore {
    private Frame frame;
    private int scoreAt;
}
