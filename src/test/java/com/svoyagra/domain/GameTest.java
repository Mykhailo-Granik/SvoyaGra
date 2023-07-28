package com.svoyagra.domain;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void shouldReturnPlayerWasAParticipantIfItIsInTheResults() {
        Player player = new Player(1, "John", "Doe", "Vinny");
        Game game = new Game(1, "Game", "Description", Map.of(player, new Game.PlayerResult(100, 1)));
        assertTrue(game.hadParticipant(player));
    }

    @Test
    public void shouldReturnPlayerWasNotAParticipantIfItIsNotInTheResults() {
        Player player = new Player(1, "John", "Doe", "Vinny");
        Player otherPlayer = new Player(2, "Jane", "Doe", "Jane");
        Game game = new Game(1, "Game", "Description", Map.of(otherPlayer, new Game.PlayerResult(100, 1)));
        assertFalse(game.hadParticipant(player));
    }

}