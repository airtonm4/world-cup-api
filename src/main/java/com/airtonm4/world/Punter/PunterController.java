package com.airtonm4.world.Punter;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PunterController {

    private PunterRepository repository;

    PunterController(PunterRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/punter")
    List<Punter> allPunter() {
        return repository.findAll();
    }

    @PostMapping("/api/punter")
    Punter newPunter(@RequestBody Punter newPunter) {
        return this.repository.save(newPunter);
    }

    @GetMapping("/api/punter/{id}")
    Punter onePunter(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @DeleteMapping("/api/punter/{id}")
    void deletePunter(@PathVariable Long id) {
        repository.deleteById(id);
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
                    return repository.save(punter);
                })
                .orElseGet(() -> {
                    newPunter.setId(id);
                    return repository.save(newPunter);
                });
    }
}
