package com.emramirez.bowling.model;

import lombok.Data;

@Data
public class FrameScore {
    private Frame frame;
    private int scoreAt;
}
