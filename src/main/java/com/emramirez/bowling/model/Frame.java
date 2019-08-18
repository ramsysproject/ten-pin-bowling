package com.emramirez.bowling.model;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
public class Frame {
    private int round;
    private int firstPinFalls;
    private int secondPinFalls;
    private int thirdPinFalls;

    public boolean isSpare() {
        if (!isStrike()) {
            return sumRegularPlay() == 10;
        }

        return false;
    }

    public boolean isStrike() {
        return firstPinFalls == 10;
    }

    public int sumFallenPins() {
        return firstPinFalls + secondPinFalls + thirdPinFalls;
    }

    public int sumRegularPlay() {
        return firstPinFalls + secondPinFalls;
    }

    public List<Integer> getFallenPinsAsList() {
        return Arrays.asList(firstPinFalls, secondPinFalls, thirdPinFalls);
    }
}
