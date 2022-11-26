package com.airtonm4.world.Punter;

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

import com.airtonm4.world.Games.Game;

@RestController
public class PunterController {

    @PersistenceContext
    private EntityManager entityManager;

    private PunterRepository repository;

    PunterController(PunterRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/punter")
    List<Punter> allPunter() {
        return repository.findAll();
    }

    @GetMapping("/api/punter-by-game/{id}")
    List<Punter> getByGame(@PathVariable Long id) {
        List<Punter> punters = entityManager
                .createQuery("select p from Punter p, Game g where g.id = :p and p.game = g").setParameter("p", id)
                .getResultList();
        return punters;
    }

    @GetMapping("/api/punter/{id}")
    Punter onePunter(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/api/punter/{id}")
    String newPunter(@PathVariable Long id, @RequestBody Punter newPunter) {

        Game game = entityManager.getReference(Game.class, id);

        Punter punter = new Punter(newPunter.getName(), newPunter.getGuess(), newPunter.getPaid(), game);

        this.repository.save(punter);

        return "sucess";
    }

    @DeleteMapping("/api/punter/{id}")
    void deletePunter(@PathVariable Long id) {
        entityManager.createQuery("delete from Punter p, Game g where p = g");
    }

    @DeleteMapping("/api/punter/all")
    void deleteAllPunters() {
        repository.deleteAll();
    }

    @PutMapping("/api/punter/")
    Punter replacePunter(@RequestBody Punter newPunter, @PathVariable Long id) {
        return repository.findById(id)
                .map(punter -> {
                    punter.setName(newPunter.getName());
                    punter.setGuess(newPunter.getGuess());
                    punter.setPaid(newPunter.getPaid());
                    return repository.save(punter);
                })
                .orElseGet(() -> {
                    newPunter.setId(id);
                    return repository.save(newPunter);
                });
    }
}
