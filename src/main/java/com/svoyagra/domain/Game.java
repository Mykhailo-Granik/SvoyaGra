package com.svoyagra.domain;

import java.util.Map;

public class Game {

    private final Integer id;
    private final String name;
    private final String description;
    private final Map<Player, PlayerResult> results;

    public Game(Integer id, String name, String description, Map<Player, PlayerResult> results) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.results = results;
    }

    public boolean hadParticipant(Player player) {
        return results.containsKey(player);
    }

    public static class PlayerResult {
        private final Integer points;
        private final Integer place;

        public PlayerResult(Integer points, Integer place) {
            this.points = points;
            this.place = place;
        }
    }

}
