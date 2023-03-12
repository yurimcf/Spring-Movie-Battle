package br.ada.americanas.moviebattle.battle;

import br.ada.americanas.moviebattle.movie.Movie;
import br.ada.americanas.moviebattle.player.Player;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
public class Battle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @NotNull
    private Player player;

    @ManyToOne
    @NotNull
    private Movie left;

    @ManyToOne
    @NotNull
    private Movie right;

    @ManyToOne
    private Movie selected;

    private Boolean hit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Movie getLeft() {
        return left;
    }

    public void setLeft(Movie left) {
        this.left = left;
    }

    public Movie getRight() {
        return right;
    }

    public void setRight(Movie right) {
        this.right = right;
    }

    public Movie getSelected() {
        return selected;
    }

    public void setSelected(Movie selected) {
        this.selected = selected;
    }

    public Boolean getHit() {
        return hit;
    }

    public void setHit(Boolean hit) {
        this.hit = hit;
    }
}
