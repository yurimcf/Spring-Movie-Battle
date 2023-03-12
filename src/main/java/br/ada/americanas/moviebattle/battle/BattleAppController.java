package br.ada.americanas.moviebattle.battle;

import br.ada.americanas.moviebattle.movie.Movie;
import br.ada.americanas.moviebattle.player.Player;
import br.ada.americanas.moviebattle.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/app/battles")
public class BattleAppController {

    private BattleService battleService;
    private PlayerService playerService;

    @Autowired
    public BattleAppController(
            BattleService battleService,
            PlayerService playerService
    ) {
        this.battleService = battleService;
        this.playerService = playerService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Player> players = (List<Player>) playerService.list();
        //Ordenada a lista baseado no nome do jogador
        Collections.sort(players, Comparator.comparing(Player::getName));

        model.addAttribute("players", players);
        model.addAttribute("player", new Player());
        return "battle/form";
    }

    @PostMapping
    public String save(
            @ModelAttribute Player player,
            Model model
    ) {
        Battle battle = battleService.create(player);
        model.addAttribute("battle", battle);
        model.addAttribute("selected_movie", new Movie());
        return "battle/play";
    }

    @PostMapping("/{id}/answer")
    public String answer(
            @PathVariable("id") Long id,
            @ModelAttribute Movie selected
    ) {
        Battle battle = battleService.find(id).get();
        battleService.answer(battle, selected);
        boolean hit = battle.getHit();
        // IF ternario
        return hit ? "battle/congrats" : "battle/wasted";
    }

}
