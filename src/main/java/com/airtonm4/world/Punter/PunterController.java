package com.airtonm4.world.Punter;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PunterController {

    private PunterRepository repository;

    PunterController(PunterRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/punter")
    List<Punter> all() {
        return repository.findAll();
    }

    @PostMapping("/api/punter")
    Punter newPunter(@RequestBody Punter newPunter) {
        return this.repository.save(newPunter);
    }
}
