package com.emramirez.bowling.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Frame {
    private int round;
    private int firstPinFalls;
    private int secondPinFalls;
    private int thirdPinFalls;

    public boolean isSpare() {
        if (round != 10 && firstPinFalls != 10) {
            return firstPinFalls + secondPinFalls == 10;
        }

        return false;
    }

    public boolean isStrike() {
        if (round != 10 && firstPinFalls == 10) {
            return true;
        }

        return false;
    }
}
