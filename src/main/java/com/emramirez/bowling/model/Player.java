package com.emramirez.bowling.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {

    private String name;

    public Player(String name) {
        this.name = name;
    }
}
