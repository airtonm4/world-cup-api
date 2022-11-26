package com.airtonm4.world.Punter;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.airtonm4.world.Games.Game;

@Entity
@Table(name = "punters")
public class Punter {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "game_id", nullable = false)
    private Game games;

    @Column(name = "name")
    private String name;

    public Punter() {
    }

    public Punter(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Punter punter = (Punter) o;
        return Objects.equals(id, punter.id) &&
                Objects.equals(name, punter.name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Punter{" +
                "id=" + id +
                ", name=" + name +
                "}";

    }
}
