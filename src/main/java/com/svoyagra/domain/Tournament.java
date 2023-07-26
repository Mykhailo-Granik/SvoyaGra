package com.svoyagra.domain;

import java.util.List;

public class Tournament {

    private final String name;
    private final List<Round> rounds;

    public Tournament(String name, List<Round> rounds) {
        this.name = name;
        this.rounds = rounds;
    }

}
