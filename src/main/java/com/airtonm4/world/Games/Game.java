package com.airtonm4.world.Games;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "games")
public class Game {

    private @Id @GeneratedValue Long id;
    private String firstTeam;
    private String secondTeam;

    public Game() {
    }

    public Game(String firstTeam, String secondTeam) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setFirstTeam(String firstTeam) {
        this.firstTeam = firstTeam;
    }

    public String getFirstTeam() {
        return firstTeam;
    }

    public void setSecondTeam(String secondTeam) {
        this.secondTeam = secondTeam;
    }

    public String getSecondTeam() {
        return secondTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Game game = (Game) o;
        return Objects.equals(id, game.id) &&
                Objects.equals(firstTeam, game.firstTeam) &&
                Objects.equals(secondTeam, game.secondTeam);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", firstTeam" + firstTeam +
                " vs " + secondTeam;

    }

}
