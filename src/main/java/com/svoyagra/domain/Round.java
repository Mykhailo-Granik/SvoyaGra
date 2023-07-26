package com.svoyagra.domain;

import java.util.List;

public class Round {

    private final String name;
    private final List<Game> games;

    public Round(String name, List<Game> games) {
        this.name = name;
        this.games = games;
    }

}
