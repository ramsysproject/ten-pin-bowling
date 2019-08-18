package com.emramirez.bowling.enums;

public enum RoundEnum {
    FIRST_ROUND(1),
    LAST_ROUND(10);

    private int number;

    RoundEnum(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
