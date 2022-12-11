package com.airtonm4.world.Games;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @PersistenceContext
    private EntityManager entityManager;

    private GameRepository gameRepository;

    GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/api/games")
    List<Game> gamesWithoutPunters() {
        return gameRepository.findAll();
    }

    @GetMapping("/api/game/{id}")
    Game oneGame(@PathVariable Long id) {
        return this.gameRepository.findById(id).orElse(null);
    }

    @PostMapping("/api/game")
    Game newGame(@RequestBody Game newGame) {
        return this.gameRepository.save(newGame);
    }

    @DeleteMapping("/api/game/{id}")
    void deleteGame(@PathVariable Long id) {
        gameRepository.deleteById(id);
    }

    @PutMapping("/api/game/{id}")
    Game replacePunter(@RequestBody Game newGame, @PathVariable Long id) {
        return gameRepository.findById(id)
                .map(game -> {
                    game.setFirstTeam(newGame.getFirstTeam());
                    game.setSecondTeam(newGame.getSecondTeam());
                    game.setResult(newGame.getResult());
                    return gameRepository.save(game);
                })
                .orElseGet(() -> {
                    newGame.setId(id);
                    return gameRepository.save(newGame);
                });
    }
}
