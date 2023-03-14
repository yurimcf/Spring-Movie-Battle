package br.ada.americanas.moviebattle.player;

import br.ada.americanas.moviebattle.config.ConstraintViolationToErrorsConverter;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Classe responsavel por controlar o fluxo de inforações vindo do View determinanado ações de
 * controle de paginas(web - view) e chamadas do Controller(PlayerRepository)
 */
@Controller
@RequestMapping("/app/players")
public class PlayerAppController {

    private PlayerService service;
    private ConstraintViolationToErrorsConverter exceptionConverter;

    @Autowired
    public PlayerAppController(PlayerService service, ConstraintViolationToErrorsConverter exceptionConverter) {
        this.service = service;
        this.exceptionConverter = exceptionConverter;
    }

    @GetMapping
    public String get(Model model) {
        Iterable<Player> players = service.list();
        Collections.sort((List<Player>) players, Comparator.comparing(Player::getScore).reversed());
        model.addAttribute("players", players);
        return "/player/list";
    }

    @GetMapping("/create")
    //Fornece acesso a página html que representa o formulário. O cadastro acontece pelo POST
    public String create(Model model) {
        model.addAttribute("player", new Player());
        return "player/form";
    }

    @PostMapping
    public String save(@ModelAttribute Player player, BindingResult result) {
        //vai receber um "pacode model" com os atributos de Player
        try {
            player.setScore(0.0F);
            service.add(player);
        } catch (ConstraintViolationException e) {
            exceptionConverter.convert(e.getConstraintViolations(), result);
            return "player/form";
        }
        return "redirect:/app/players";
    }

    @GetMapping("/edit/{id}") //@PathVariable pega o elemento da URL entre {}
    public String edit(@PathVariable("id") Long id, Model model) {
        Player player = service.findById(id).get(); //pega o objeto de acordo com o id
        model.addAttribute("player", player); //pega os valores dos atributos de playes e enviar para pag forms
        return "player/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        service.delete(id);
        return "redirect:/player/list";
    }
}
