package com.svoyagra.domain;

import java.util.Objects;

public class Player {

    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final String city;

    public Player(Integer id, String firstName, String lastName, String city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(firstName, player.firstName) && Objects.equals(lastName, player.lastName) && Objects.equals(city, player.city) && Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, city, id);
    }
}
