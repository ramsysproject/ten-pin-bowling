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
        if (!isStrike()) {
            return firstPinFalls + secondPinFalls == 10;
        }

        return false;
    }

    public boolean isStrike() {
        return firstPinFalls == 10;
    }

    public int sumFallenPins() {
        return firstPinFalls + secondPinFalls + thirdPinFalls;
    }
}
