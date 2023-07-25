package com.svoyagra.domain;

import java.util.Map;

public class Game {

    private final Integer id;
    private final String name;
    private final String description;
    private final Map<Player, Integer> results;

    public Game(Integer id, String name, String description, Map<Player, Integer> results) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.results = results;
    }

    public boolean hadParticipant(Player player) {
        return results.containsKey(player);
    }

}
