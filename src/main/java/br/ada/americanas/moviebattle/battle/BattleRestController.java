package br.ada.americanas.moviebattle.battle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/battles")
public class BattleRestController {
    private BattleService service;

    @Autowired
    public BattleRestController(BattleService service) {
        this.service = service;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Iterable<Battle> list() {
        return service.list();
    }

    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Battle get(@PathVariable("id") Long id) {
        return this.service.find(id).get();
    }
}
