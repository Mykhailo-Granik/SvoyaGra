package com.svoyagra.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void twoSamePlayersShouldBeEqual() {
        Player player1 = new Player(1, "John", "Doe", "New York");
        Player player2 = new Player(1, "John", "Doe", "New York");
        assertEquals(player1, player2);
    }

    @Test
    public void playersWithDifferentIdsShouldNotBeEqual() {
        Player player1 = new Player(1, "John", "Doe", "New York");
        Player player2 = new Player(2, "John", "Doe", "New York");
        assertNotEquals(player1, player2);
    }

    @Test
    public void twoSamePlayersShouldHaveTheSameHashCode() {
        Player player1 = new Player(1, "John", "Doe", "New York");
        Player player2 = new Player(1, "John", "Doe", "New York");
        assertEquals(player1.hashCode(), player2.hashCode());
    }

}