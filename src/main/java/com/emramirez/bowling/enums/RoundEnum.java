package com.emramirez.bowling.enums;

public enum RoundEnum {
    FIRST_ROUND(1),
    LAST_ROUND(10);

    private int roundNumber;

    RoundEnum(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public int getRoundNumber() {
        return roundNumber;
    }
}
