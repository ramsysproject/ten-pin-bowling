package com.emramirez.bowling.model;

import lombok.Data;

@Data
public class Frame {
    private int round;
    private int firstPinFalls;
    private int secondPinFalls;
    private int thirdPinFalls;
    private boolean isSpare;
    private boolean isStrike;
}
